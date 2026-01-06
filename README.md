# Task Manager - DevOps Project

A Spring Boot REST API demonstrating complete DevOps automation with CI/CD, security scanning, containerization, and Kubernetes orchestration.

[![CI/CD Pipeline](https://github.com/Edoto03/taskmanager-devops/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/Edoto03/taskmanager-devops/actions)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=Edoto03_taskmanager-devops&metric=alert_status)](https://sonarcloud.io/dashboard?id=Edoto03_taskmanager-devops)

---

## Application Overview

A lightweight task management REST API built with Spring Boot. The application provides CRUD operations for managing tasks with PostgreSQL persistence. The focus is on demonstrating enterprise DevOps practices rather than complex business logic.

---

## Project Structure
```
taskmanager-devops/
├── .github/
│   └── workflows/
│       ├── ci-cd.yml                      # Main CI/CD pipeline
│       └── sonar.yml                      # SonarQube analysis workflow
├── .mvn/wrapper/                          # Maven wrapper files
│   └── maven-wrapper.properties
├── k8s/
│   ├── namespace.yaml                     # Kubernetes namespace definition
│   ├── configmap.yaml                     # Application configuration
│   ├── postgres-deployment.yaml           # PostgreSQL deployment
│   ├── postgres-pvc.yaml                  # Persistent volume claim (1Gi)
│   ├── postgres-service.yaml              # PostgreSQL ClusterIP service
│   ├── deployment.yaml                    # Application deployment
│   ├── service.yaml                       # Application NodePort service
│   └── ingress.yaml                       # Ingress configuration
├── src/
│   ├── main/
│   │   ├── java/com/university/taskmanager/
│   │   │   ├── controller/
│   │   │   │   └── TaskController.java    # REST API endpoints
│   │   │   ├── model/
│   │   │   │   └── Task.java              # JPA entity
│   │   │   ├── repository/
│   │   │   │   └── TaskRepository.java    # Data access layer
│   │   │   ├── service/
│   │   │   │   └── TaskService.java       # Business logic
│   │   │   └── TaskmanagerApplication.java # Main application class
│   │   └── resources/
│   │       ├── application.properties     # Spring Boot configuration
│   │       ├── db/migration/
│   │       │   ├── V1__Create_tasks_table.sql
│   │       │   └── V2__Insert_sample_data.sql
│   │       ├── static/
│   │       │   └── index.html             # Frontend interface
│   │       └── templates/                 # (if any Thymeleaf templates)
│   └── test/
│       ├── java/com/university/taskmanager/
│       │   ├── controller/
│       │   │   └── TaskControllerTest.java
│       │   ├── service/
│       │   │   └── TaskServiceTest.java
│       │   └── TaskmanagerApplicationTests.java
│       └── resources/
│           └── application-test.properties # Test configuration
├── .dockerignore                          # Docker build exclusions
├── .gitattributes                         # Git attributes configuration
├── .gitignore                             # Git ignore rules
├── Dockerfile                             # Multi-stage container build
├── HELP.md                                # Spring Boot help documentation
├── README.md                              # Project documentation (this file)
├── docker-compose.yml                     # Local development environment
├── mvnw                                   # Maven wrapper script (Unix)
├── mvnw.cmd                               # Maven wrapper script (Windows)
├── pom.xml                                # Maven dependencies and plugins
└── sonar-project.properties               # SonarQube configuration
```

---

## DevOps Implementation

This project demonstrates 10 core DevOps practices:

| Topic | Implementation | Status |
|-------|----------------|--------|
| Source Control | Git with GitHub | Complete |
| Branching Strategy | GitFlow (main/develop/feature) | Complete |
| Build Pipeline | GitHub Actions (9 automated jobs) | Complete |
| Continuous Integration | Automated testing and quality gates | Complete |
| Continuous Delivery | Automated Docker builds and registry push | Complete |
| Security (SAST) | SonarQube, Trivy, Snyk, SpotBugs | Complete |
| Containerization | Docker multi-stage builds | Complete |
| Orchestration | Kubernetes deployments | Complete |
| Database Migrations | Flyway version control | Complete |
| Infrastructure as Code | Kubernetes manifests | Complete |

---

## Branching Strategy & Workflow

The project follows a Structured GitFlow Strategy with protection rules to ensure code quality.

**Branch Structure:**
- `main` (Production): Stable branch deployed to Kubernetes. Protected: requires Pull Request approval.
- `develop` (Integration): Staging branch where features are combined and tested. Protected: CI checks must pass.
- `feature/**` (Development): Temporary branches for specific features.

**Quality Gates:**
- Required Status Checks: CI Pipeline must pass before merging.
- Pull Request Flow: Direct commits are disabled to ensure code review.

**Workflow Diagram:**
```
feature/add-api → Pull Request → main → Kubernetes Production
       ↓                            ↓
    Lint Check                Full CI Pipeline
                              (Test + Build + Deploy)
```

---

## CI/CD Pipeline

The pipeline executes 9 automated jobs on every push to main or develop branches.

**Pipeline Jobs:**

1. **Run Tests** (~2 min): Executes 21 unit tests across all application layers.
2. **Code Coverage** (~2 min): Generates JaCoCo coverage reports (75% coverage).
3. **SonarQube Analysis** (~3 min): Performs SAST for code quality and security vulnerabilities.
4. **Code Quality** (~2 min): Runs Checkstyle and SpotBugs for standards enforcement.
5. **Snyk Scan** (~2 min): Scans Maven dependencies and Docker images for known vulnerabilities.
6. **Build Application** (~3 min): Compiles source code and packages JAR artifact.
7. **Build Docker Image** (~4 min): Multi-stage build and push to Docker Hub with versioned tags.
8. **Security Scan** (~2 min): Trivy scans Docker image for CRITICAL and HIGH vulnerabilities.
9. **Deployment Status** (~1 min): Summary of all job results.

**Total Pipeline Duration:** ~20 minutes

**Pipeline Efficiency:**
- Path filtering ignores documentation changes (`**.md`) to prevent unnecessary builds.
- Smart triggers reduce GitHub Actions compute usage.

View live pipeline: [GitHub Actions](https://github.com/Edoto03/taskmanager-devops/actions)

---

## Security Implementation

### Static Application Security Testing (SAST)

**SonarQube**
- **Purpose:** Code quality and security analysis
- **Integration:** Runs on every commit via dedicated workflow
- **Detects:** SQL injection, XSS vulnerabilities, code smells, technical debt
- **Dashboard:** [SonarCloud](https://sonarcloud.io/dashboard?id=Edoto03_taskmanager-devops)

**Trivy**
- **Purpose:** Container vulnerability scanning
- **Integration:** Scans every Docker image before deployment
- **Detects:** OS package vulnerabilities, application dependency CVEs
- **Database:** National Vulnerability Database (NVD)

**Snyk**
- **Purpose:** Dependency vulnerability scanning
- **Integration:** Scans Maven dependencies and Docker base images
- **Detects:** Known vulnerabilities in third-party libraries

**SpotBugs**
- **Purpose:** Java static code analysis
- **Integration:** Runs during code quality job
- **Detects:** Null pointer dereferences, resource leaks, SQL injection risks

**Why SAST Matters:**
- Early Detection: Identifies vulnerabilities during development
- Cost Reduction: Fixes are 10x cheaper in development vs. production
- Compliance: Meets industry standards (OWASP, PCI-DSS)

---

## Docker Configuration

The application is containerized using Docker with security and performance optimizations.

**Security Implementation:**
- Minimal Base Image: Uses `eclipse-temurin:21-jre-jammy` to reduce attack surface.
- Non-Root Execution: Application runs as dedicated user to prevent privilege escalation.
- Multi-Stage Build: Separates build and runtime environments for smaller final image.

**Build Performance:**
- Layer Caching: Dependencies are installed before source code to leverage Docker caching.
- Dependency Separation: `pom.xml` copied independently for optimal cache utilization.

---

## Kubernetes Deployment

The application runs on a Kubernetes cluster with high availability and self-healing capabilities.

**Resources Deployed:**
```yaml
Namespace: taskmanager
Deployments: postgres (1 replica), taskmanager (1 replica)
Services: postgres-service (ClusterIP), taskmanager-service (NodePort:30080)
ConfigMaps: taskmanager-config (environment variables)
Storage: postgres-pvc (1Gi persistent volume)
```

**Key Features:**
- Self-Healing: Automatic pod restart on failure
- Rolling Updates: Zero-downtime deployments
- Resource Limits: CPU and memory constraints
- Health Probes: Liveness and readiness checks
- Init Containers: Database dependency management
- Persistent Storage: Database survives pod restarts

**Management Commands:**
```bash
# Deploy all resources
kubectl apply -f k8s/

# Verify deployment
kubectl get pods -n taskmanager

# View logs
kubectl logs -f deployment/taskmanager -n taskmanager

# Access application
curl http://localhost:30080/api/tasks
```

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Retrieve all tasks |
| GET | `/api/tasks/{id}` | Retrieve specific task |
| POST | `/api/tasks` | Create new task |
| PUT | `/api/tasks/{id}` | Update existing task |
| DELETE | `/api/tasks/{id}` | Delete task |

**Example Request:**
```bash
# Create task
curl -X POST http://localhost:30080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete DevOps project",
    "description": "Finish all 10 topics",
    "status": "IN_PROGRESS"
  }'

# Get all tasks
curl http://localhost:30080/api/tasks
```

---

## Database Schema
```sql
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

**Migration History:**
- V1: Create tasks table with indexes
- V2: Insert sample data (8 tasks)

Migrations are managed by Flyway and execute automatically on application startup.

---

## How to Run

### 1. Running from Source
```bash
# Clone repository
git clone https://github.com/Edoto03/taskmanager-devops.git
cd taskmanager-devops

# Start PostgreSQL
docker-compose up -d

# Run application
mvnw spring-boot:run

# Access API
curl http://localhost:8080/api/tasks
```

### 2. Local Testing (Docker)
```bash
# Build Docker image
docker build -t taskmanager .

# Run container
docker run -p 8080:8080 taskmanager

# Access at http://localhost:8080
```

### 3. Kubernetes Deployment
```bash
# Create namespace and deploy resources
kubectl apply -f k8s/

# Verify deployment
kubectl get pods -n taskmanager

# Access application
curl http://localhost:30080/api/tasks
```

---

## Technologies Used

| Category | Technology |
|----------|-----------|
| Core Framework | Java 21, Spring Boot 3.2.0 |
| Data Persistence | Spring Data JPA, PostgreSQL 15, Flyway 9.22 |
| CI/CD Automation | GitHub Actions |
| Containerization | Docker, Docker Compose |
| Orchestration | Kubernetes |
| Security (SAST) | SonarQube, Trivy, Snyk, SpotBugs |
| Quality Assurance | JUnit 5, Mockito, JaCoCo (75% coverage) |
| Build Tool | Maven 3.8 |

---

## Future Improvements

- **Monitoring & Observability:** Implement Prometheus and Grafana for metrics collection and visualization.
- **Log Aggregation:** Deploy ELK stack (Elasticsearch, Logstog, Kibana) for centralized logging.
