TechQuarter Corporate Booking Tool — Mono-repo

Overview
- Backend: Spring Boot 3 Workflow Service (clean layering)
- Frontend: React (Vite) with RN‑friendly separation (api/services/models/screens/components)
- Infra: Docker-ready, AWS App Runner (backend) + S3/CloudFront (frontend)

Architecture (Backend)
Package: com.techquarter.booking
- api: REST controllers, DTOs, global error handling
- application: use cases/services (Employee, Booking, Search, HotelAppointment)
- domain: entities + repository ports
- infrastructure: in-memory adapters (ConcurrentHashMap), web filter (Correlation ID)

Domain
- Entities: Employee, Booking, BookingOption, HotelAppointment
- Enum: ResourceType { FLIGHT, HOTEL }
- Persistence: In-memory repositories (prepared for future DB adapter)

API Endpoints
- POST /employees
- POST /search
- POST /bookings
- POST /hotel-appointments
- GET  /bookings?employeeId=EMP123

Validation & Errors
- jakarta.validation on DTOs; 400 on validation errors with field map
- 404 for missing Employee on dependent operations
- Global @RestControllerAdvice
- Correlation ID via X-Correlation-Id filter (auto-generates if absent)

OpenAPI
- SpringDoc enabled; Swagger UI at /swagger-ui.html (when backend is running)

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- Node.js 18+

### Step 1: Start the Backend

Open a terminal and run:
```bash
cd backend
mvn spring-boot:run
```

Wait until you see: `Started BookingApplication`

The backend will be available at:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

### Step 2: Start the Frontend

Open a **new terminal** and run:
```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at: http://localhost:5173

### Step 3: Test the Workflow

1. Open http://localhost:5173 in your browser
2. **Register Employee**: Fill in ID (e.g., `EMP1`), name, email → click Register
3. **Create Booking**: Use the same Employee ID, select FLIGHT/HOTEL, fill details → click Create
4. **View Bookings**: Enter the Employee ID → click Load

### Alternative: Run with Docker

```bash
# Build and run backend
docker build -t booking-backend ./backend
docker run -p 8080:8080 booking-backend

# Build and run frontend (in another terminal)
docker build -t booking-frontend ./frontend
docker run -p 80:80 -e VITE_API_BASE_URL=http://localhost:8080 booking-frontend
```

### Run Tests

```bash
# Backend tests
cd backend && mvn test

# Or from root
mvn -f backend/pom.xml test
```

Minimal Frontend Screens
- RegisterEmployee, CreateBooking, BookingsList, HotelAppointment

Next Hardening Steps
- Replace in-memory repos with a proper DB adapter (e.g., Postgres via Spring Data JDBC)
- Add authentication/authorization and rate limiting
- Expand tests (contract tests for controllers, service edge cases, repository adapter tests)
- CI/CD pipeline (build, test, dockerize, deploy)
- Observability: structured logs with correlationId, metrics, tracing
