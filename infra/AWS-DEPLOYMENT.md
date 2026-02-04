# AWS Deployment Guide

## Architecture

```
                    ┌─────────────────────────────────────┐
                    │            CloudFront               │
                    │              (CDN)                  │
                    └─────────────┬───────────────────────┘
                                  │
                    ┌─────────────▼───────────────────────┐
                    │         S3 Bucket                   │
                    │     (Frontend Static Files)         │
                    └─────────────────────────────────────┘

                    ┌─────────────────────────────────────┐
                    │         AWS App Runner              │
                    │          (Backend API)              │
                    │                                     │
                    │  ┌─────────────────────────────┐   │
                    │  │    ECR Container Image      │   │
                    │  └─────────────────────────────┘   │
                    └─────────────────────────────────────┘
```

## Prerequisites

- AWS CLI installed and configured
- Docker installed
- AWS Account with appropriate permissions

## Step 1: Deploy Backend to App Runner

### 1.1 Create ECR Repository

```bash
aws ecr create-repository --repository-name booking-backend --region us-east-1
```

### 1.2 Build and Push Docker Image

```bash
# Get ECR login
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com

# Build image
cd backend
docker build -t booking-backend .

# Tag and push
docker tag booking-backend:latest <ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/booking-backend:latest
docker push <ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/booking-backend:latest
```

### 1.3 Create App Runner Service

```bash
aws apprunner create-service \
  --service-name booking-backend \
  --source-configuration '{
    "ImageRepository": {
      "ImageIdentifier": "<ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/booking-backend:latest",
      "ImageRepositoryType": "ECR",
      "ImageConfiguration": {
        "Port": "8080"
      }
    },
    "AutoDeploymentsEnabled": true,
    "AuthenticationConfiguration": {
      "AccessRoleArn": "arn:aws:iam::<ACCOUNT_ID>:role/AppRunnerECRAccessRole"
    }
  }' \
  --instance-configuration '{
    "Cpu": "1024",
    "Memory": "2048"
  }' \
  --health-check-configuration '{
    "Protocol": "HTTP",
    "Path": "/actuator/health",
    "Interval": 10,
    "Timeout": 5,
    "HealthyThreshold": 1,
    "UnhealthyThreshold": 5
  }'
```

Note the Service URL from the output (e.g., `https://xxxxx.us-east-1.awsapprunner.com`).

## Step 2: Deploy Frontend to S3 + CloudFront

### 2.1 Create S3 Bucket

```bash
aws s3 mb s3://booking-frontend-<unique-suffix> --region us-east-1
```

### 2.2 Build Frontend with Backend URL

```bash
cd frontend
VITE_API_BASE_URL=https://xxxxx.us-east-1.awsapprunner.com npm run build
```

### 2.3 Upload to S3

```bash
aws s3 sync dist/ s3://booking-frontend-<unique-suffix> --delete
```

### 2.4 Create CloudFront Distribution

```bash
aws cloudfront create-distribution \
  --origin-domain-name booking-frontend-<unique-suffix>.s3.amazonaws.com \
  --default-root-object index.html
```

## Step 3: Configure CORS on Backend

Update `CorsConfig.java` to allow the CloudFront domain:

```java
config.setAllowedOrigins(List.of(
    "https://xxxxx.cloudfront.net",
    "http://localhost:5173"
));
```

Rebuild and redeploy the backend.

## Cost Estimate (Low Traffic)

| Service | Monthly Cost |
|---------|-------------|
| App Runner (1 vCPU, 2GB) | $5-15 |
| S3 | $0.50 |
| CloudFront | $1-5 |
| ECR | $1 |
| **Total** | **~$10-25/month** |

## Scaling for 100 TPS

App Runner automatically scales based on traffic:
- Min instances: 1
- Max instances: 25 (configurable)
- Concurrent requests per instance: 80-100

For sustained 100 TPS:
- Estimate 2-3 instances running
- Consider adding RDS for persistent storage
- Add ElastiCache for session/caching if needed

## Cleanup

```bash
# Delete App Runner service
aws apprunner delete-service --service-arn <SERVICE_ARN>

# Empty and delete S3 bucket
aws s3 rm s3://booking-frontend-<unique-suffix> --recursive
aws s3 rb s3://booking-frontend-<unique-suffix>

# Delete CloudFront distribution (disable first, then delete)
aws cloudfront delete-distribution --id <DISTRIBUTION_ID> --if-match <ETAG>

# Delete ECR repository
aws ecr delete-repository --repository-name booking-backend --force
```
