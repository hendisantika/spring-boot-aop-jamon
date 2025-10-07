# Spring Boot Monitoring with Grafana & Prometheus

This project includes monitoring setup using Grafana and Prometheus.

## Quick Start

1. **Start the monitoring stack:**
   ```bash
   docker compose up -d
   ```

2. **Start your Spring Boot application:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the services:**
    - **Grafana**: http://localhost:3000
        - Username: `admin`
        - Password: `admin`
    - **Prometheus**: http://localhost:9090
    - **Spring Boot Actuator**: http://localhost:8080/actuator
    - **Prometheus Metrics**: http://localhost:8080/actuator/prometheus

## What's Included

### Docker Services

- **PostgreSQL**: Database on port 5433
- **Prometheus**: Metrics collection on port 9090
- **Grafana**: Visualization on port 3000

### Monitoring Features

The Spring Boot application exposes the following metrics endpoints:

- `/actuator/health` - Application health status
- `/actuator/metrics` - Available metrics
- `/actuator/prometheus` - Prometheus-formatted metrics

### Dashboard Panels

The pre-configured Grafana dashboard includes:

1. **HTTP Request Rate** - Requests per second by endpoint
2. **HTTP Request Duration** - Average response time
3. **JVM Memory Used** - Heap and non-heap memory usage
4. **CPU Usage** - System and process CPU utilization
5. **JVM Threads** - Live and daemon thread counts
6. **Database Connections** - HikariCP connection pool metrics

## Configuration Files

- `compose.yaml` - Docker Compose configuration
- `prometheus/prometheus.yml` - Prometheus scrape configuration
- `grafana/provisioning/datasources/prometheus.yml` - Grafana datasource
- `grafana/provisioning/dashboards/dashboard.yml` - Dashboard provisioning
- `grafana/dashboards/spring-boot-dashboard.json` - Pre-built dashboard

## Stopping the Services

```bash
docker compose down
```

To remove volumes as well:

```bash
docker compose down -v
```
