# Translation Management Service

## Overview
A Spring Boot application with Apache Camel for managing translations across multiple locales with tagging and JSON export capabilities.

## Setup Instructions
1. **Prerequisites**: Java 21, Maven, Docker (optional)
2. **Clone Repository**:
   ```bash
   git clone <repository-url>
   cd TranslationManagementService
   ```
3. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. **Docker Setup**:
   ```bash
   docker build -t tms .
   docker run -p 8080:8080 tms
   ```
5. **Access Swagger UI**: Open `http://localhost:8080/swagger-ui.html`
6. **Generate Token**: Use `/api/auth/login` to get JWT token (implement as needed).

## Design Choices
- **Apache Camel**: Used for routing API requests to services, enabling loose coupling and scalability.
- **H2 Database**: In-memory database for testing, with 100k+ records populated via `data.sql`.
- **JWT Authentication**: Secures all endpoints except auth and Swagger.
- **Performance**: Optimized JPA queries with indexing; JSON export uses streaming for large datasets.
- **SOLID Principles**: Single responsibility (services, repositories), dependency injection, and interface-based design.

## Endpoints
- `POST /api/translations`: Create a translation
- `PUT /api/translations/{id}`: Update a translation
- `GET /api/translations/{id}`: View a translation
- `GET /api/translations/search?keyword={keyword}`: Search translations
- `GET /api/translations/locale/{locale}`: Get translations by locale
- `GET /api/translations/export/{locale}`: Export translations as JSON

## Performance Notes
- Response times target <200ms for CRUD, <500ms for JSON export.
- Database schema optimized with indexes on `key`, `locale`, and `tags`.
