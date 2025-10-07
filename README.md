# Spring Boot AOP JAMon

A Spring Boot application demonstrating Aspect-Oriented Programming (AOP) with JAMon (Java Application Monitor) for
performance monitoring and metrics collection, integrated with Prometheus and Grafana for comprehensive observability.

## Features

- **AOP-based Performance Monitoring**: Custom `@MethodMonitor` annotation for tracking method execution times
- **JAMon Integration**: Real-time performance metrics and monitoring
- **PostgreSQL Database**: Account management with JDBC (100 anime character accounts)
- **Spring Boot Actuator**: Health checks and application metrics
- **Prometheus Metrics**: Micrometer registry for Prometheus integration
- **Grafana Dashboards**: Pre-configured dashboards for visualization
- **External API Integration**: Google Books API integration
- **Docker Compose**: Complete monitoring stack with PostgreSQL, Prometheus, and Grafana

## Prerequisites

- Java 25
- Maven 3.x
- Docker & Docker Compose

## Quick Start

### 1. Start All Services (Database + Monitoring Stack)

```bash
docker compose up -d
```

This will start:

**PostgreSQL 17.5**
- **Username**: `yu71`
- **Password**: `53cret`
- **Database**: `account`
- **Port**: `5433`
- **Data**: 100 pre-loaded accounts with anime characters (One Piece, Jujutsu Kaisen, Demon Slayer, Naruto)

**Prometheus**
- **Port**: `9090`
- **Access**: http://localhost:9090
- **Config**: Auto-scrapes Spring Boot metrics every 5 seconds

**Grafana**
- **Port**: `3000`
- **Access**: http://localhost:3000
- **Credentials**: admin/admin
- **Dashboard**: Pre-configured Spring Boot monitoring dashboard

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

## Monitoring & Observability

### Prometheus Metrics

Access Prometheus at http://localhost:9090 to:
- Query custom metrics
- View scrape targets
- Check metric trends

Available metrics include:
- `http_server_requests_seconds` - HTTP request duration
- `jvm_memory_used_bytes` - JVM memory usage
- `system_cpu_usage` - System CPU usage
- `process_cpu_usage` - Process CPU usage
- `jvm_threads_live_threads` - Thread count
- `hikaricp_connections` - Database connection pool metrics

### Grafana Dashboards

Access Grafana at http://localhost:3000 (admin/admin)

The pre-configured dashboard includes:
1. **HTTP Request Rate** - Requests per second by endpoint
2. **HTTP Request Duration** - Average response time
3. **JVM Memory Used** - Heap and non-heap memory usage
4. **CPU Usage** - System and process CPU utilization
5. **JVM Threads** - Live and daemon thread counts
6. **Database Connections** - HikariCP connection pool metrics

### Viewing Real-time Metrics

1. Start the application: `./mvnw spring-boot:run`
2. Make some requests: `curl http://localhost:8080/account`
3. Open Grafana: http://localhost:3000
4. Navigate to "Spring Boot AOP JAMon Dashboard"
5. Watch metrics update in real-time

## Database Schema

The `account` table schema matches the `Account.java` model:

```sql
CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    account_holder VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    opening_date DATE DEFAULT CURRENT_DATE,
    email VARCHAR(100) UNIQUE NOT NULL
);
```

### Sample Data

The database is pre-loaded with 100 accounts featuring characters from popular anime:

- **One Piece** (25): Luffy, Zoro, Nami, Shanks, Mihawk, Law, Ace, etc.
- **Jujutsu Kaisen** (25): Yuji, Gojo, Sukuna, Megumi, Nobara, Nanami, etc.
- **Demon Slayer** (25): Tanjiro, Nezuko, Rengoku, Giyu, Muzan, Akaza, etc.
- **Naruto** (25): Naruto, Sasuke, Kakashi, Itachi, Madara, Minato, etc.

Query examples:
```bash
# View top accounts by balance
docker exec spring-boot-aop-jamon-postgres psql -U yu71 -d account \
  -c "SELECT account_holder, balance FROM account ORDER BY balance DESC LIMIT 10;"

# Count accounts by anime series
docker exec spring-boot-aop-jamon-postgres psql -U yu71 -d account \
  -c "SELECT COUNT(*) FROM account WHERE email LIKE '%onepiece.com';"
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

### Check Container Status

```bash
docker compose ps
```

### View Logs

```bash
# All services
docker compose logs -f

# Specific service
docker compose logs -f postgres
docker compose logs -f prometheus
docker compose logs -f grafana
```

### Connect to PostgreSQL

```bash
docker exec -it spring-boot-aop-jamon-postgres psql -U yu71 -d account
```

### Stop All Services

```bash
docker compose down
```

### Clean Restart (Remove All Data)

```bash
docker compose down
rm -rf postgres_data prometheus_data grafana_data
docker compose up -d
```

### Restart Specific Service

```bash
docker compose restart postgres
docker compose restart prometheus
docker compose restart grafana
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
├── prometheus/
│   └── prometheus.yml               # Prometheus configuration
├── grafana/
│   ├── provisioning/
│   │   ├── datasources/            # Datasource configs
│   │   └── dashboards/             # Dashboard provisioning
│   └── dashboards/
│       └── spring-boot-dashboard.json  # Pre-built dashboard
├── compose.yaml                      # Docker Compose configuration
├── schema.sql                        # Database initialization script
├── README.md                         # This file
├── README-MONITORING.md             # Monitoring setup guide
├── CONTAINER-STATUS.md              # Container status report
└── pom.xml
```

## Technologies Used

- **Spring Boot 3.5.6**
- **Java 25**
- **PostgreSQL 17.5**
- **JAMon 2.82** - Performance monitoring
- **AspectJ 1.9.24** - AOP framework
- **Spring JDBC** - Database access
- **Micrometer + Prometheus** - Metrics collection and export
- **Prometheus** - Time-series metrics database
- **Grafana** - Metrics visualization and dashboards
- **Spring Boot Actuator** - Application monitoring and management
- **Lombok** - Reduce boilerplate code
- **Docker Compose** - Container orchestration

## Additional Documentation

- **[README-MONITORING.md](README-MONITORING.md)** - Detailed monitoring setup and configuration
- **[CONTAINER-STATUS.md](CONTAINER-STATUS.md)** - Current container status and health checks

## Author

Created by **Hendi Santika**

- Email: hendisantika@yahoo.co.id
- Telegram: @hendisantika34
- Link: s.id/hendisantika

## License

This project is created for educational and demonstration purposes.
