# Spring Boot AOP JAMon

A Spring Boot application demonstrating Aspect-Oriented Programming (AOP) with JAMon (Java Application Monitor) for
performance monitoring and metrics collection.

## Features

- **AOP-based Performance Monitoring**: Custom `@MethodMonitor` annotation for tracking method execution times
- **JAMon Integration**: Real-time performance metrics and monitoring
- **PostgreSQL Database**: Account management with JDBC
- **Spring Boot Actuator**: Health checks and application metrics
- **Prometheus Metrics**: Micrometer registry for Prometheus integration
- **External API Integration**: Google Books API integration

## Prerequisites

- Java 25
- Maven 3.x
- Docker & Docker Compose

## Quick Start

### 1. Start PostgreSQL Database

```bash
docker-compose -f compose.yaml up -d
```

This will start PostgreSQL 17.5 with:

- **Username**: `yu71`
- **Password**: `53cret`
- **Database**: `account`
- **Port**: `5433` (mapped from container's 5432)

The database will be automatically initialized with the `account` table and sample data.

### 2. Build the Application

```bash
mvn clean package
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR directly:

```bash
java -jar target/spring-boot-aop-jamon-0.0.1-SNAPSHOT.jar
```

The application will start on port `8080`.

## API Endpoints

### Account Endpoints

#### Get All Accounts

```bash
curl -X GET http://localhost:8080/account
```

**Response:**

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "balance": 1000.00,
    "created_at": "2025-10-06T23:26:19.957370"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "balance": 2500.50,
    "created_at": "2025-10-06T23:26:19.957370"
  }
]
```

### Book Endpoints

#### Get Books (Google Books API)

```bash
curl -X GET http://localhost:8080/book
```

**Response:**

```json
{
  "kind": "books#volumes",
  "totalItems": 100,
  "items": [...]
}
```

### Monitor Endpoints

#### Get Monitor Details

Get performance metrics for a specific monitored endpoint:

```bash
# Get account.list monitor
curl -X GET http://localhost:8080/monitor/account.list
```

**Response:**

```
Label=account.list, Units=ms., Hits=5, Total=125.5, Avg=25.1, Min=15.2, Max=45.3, Active=0, LastValue=20.5
```

```bash
# Get book.list monitor
curl -X GET http://localhost:8080/monitor/book.list
```

### Actuator Endpoints

#### Health Check

```bash
curl -X GET http://localhost:8080/actuator/health
```

**Response:**

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP"
    },
    "diskSpace": {
      "status": "UP"
    }
  }
}
```

#### Prometheus Metrics

```bash
curl -X GET http://localhost:8080/actuator/prometheus
```

#### All Actuator Endpoints

```bash
curl -X GET http://localhost:8080/actuator
```

## Configuration

Application configuration is in `src/main/resources/application.properties`:

```properties
spring.application.name=spring-boot-aop-jamon
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5433/account
spring.datasource.username=yu71
spring.datasource.password=53cret

# Actuator Configuration
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
management.metrics.enable.http.server.requests=false
```

## Database Schema

The `account` table schema:

```sql
CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## How It Works

### AOP Method Monitoring

The application uses a custom `@MethodMonitor` annotation to automatically track method execution times:

```java
@GetMapping
@MethodMonitor(name = "account.list", uri = "/account")
public ResponseEntity<List<Account>> account() {
    // Method implementation
}
```

JAMon collects metrics including:

- Number of hits (executions)
- Total execution time
- Average execution time
- Min/Max execution times
- Active executions
- Last execution time

### Custom Health Indicators

The application includes custom health indicators for monitoring account-related health status.

## Testing Examples

### Complete Workflow Test

```bash
# 1. Check application health
curl http://localhost:8080/actuator/health

# 2. Fetch accounts (this will be monitored)
curl http://localhost:8080/account

# 3. Fetch books (this will be monitored)
curl http://localhost:8080/book

# 4. Check performance metrics for account endpoint
curl http://localhost:8080/monitor/account.list

# 5. Check performance metrics for book endpoint
curl http://localhost:8080/monitor/book.list

# 6. View all Prometheus metrics
curl http://localhost:8080/actuator/prometheus
```

### Pretty Print JSON Responses

Using `jq` for formatted output:

```bash
# Pretty print accounts
curl -s http://localhost:8080/account | jq

# Pretty print health check
curl -s http://localhost:8080/actuator/health | jq

# Pretty print books
curl -s http://localhost:8080/book | jq
```

## Docker Management

### View PostgreSQL Logs

```bash
docker logs spring-boot-aop-jamon-postgres
```

### Connect to PostgreSQL

```bash
docker exec -it spring-boot-aop-jamon-postgres psql -U yu71 -d account
```

### Stop the Database

```bash
docker-compose -f compose.yaml down
```

### Remove All Data and Restart Fresh

```bash
docker-compose -f compose.yaml down -v
rm -rf postgres_data
docker-compose -f compose.yaml up -d
```

## Project Structure

```
spring-boot-aop-jamon/
├── src/
│   └── main/
│       ├── java/id/my/hendisantika/springbootaopjamon/
│       │   ├── annotation/          # Custom annotations
│       │   ├── aspect/              # AOP aspects
│       │   ├── config/              # Configuration classes
│       │   ├── controller/          # REST controllers
│       │   ├── model/               # Data models
│       │   └── service/             # Business services
│       └── resources/
│           └── application.properties
├── compose.yaml                      # Docker Compose configuration
├── schema.sql                        # Database initialization script
└── pom.xml
```

## Technologies Used

- **Spring Boot 3.5.6**
- **Java 25**
- **PostgreSQL 17.5**
- **JAMon 2.82** - Performance monitoring
- **AspectJ 1.9.22** - AOP framework
- **Spring JDBC** - Database access
- **Micrometer + Prometheus** - Metrics collection
- **Spring Boot Actuator** - Application monitoring
- **Lombok** - Reduce boilerplate code

## Author

Created by **Hendi Santika**

- Email: hendisantika@yahoo.co.id
- Telegram: @hendisantika34
- Link: s.id/hendisantika

## License

This project is created for educational and demonstration purposes.
