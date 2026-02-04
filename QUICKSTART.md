# Quick Start

## One-Click Start with Docker

```bash
docker-compose up --build
```

Once started:
- **Frontend**: http://localhost
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health

## Test the Workflow

1. Open http://localhost in your browser
2. **Register Employee**: Fill in ID (e.g., `EMP1`), name, email → click Register
3. **Create Booking**: Use the same Employee ID, fill in destination, dates, etc. → click Create
4. **View Bookings**: Enter the Employee ID → click Load

## Stop

```bash
docker-compose down
```

## Without Docker

See [README.md](README.md) for manual setup instructions.
