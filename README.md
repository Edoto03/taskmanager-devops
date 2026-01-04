# Task Manager - DevOps Final Project 🚀

Complete CI/CD pipeline with automated testing, security scanning, Docker, Kubernetes, and SonarQube code analysis.

[![CI/CD Pipeline](https://github.com/Edoto03/taskmanager-devops/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/Edoto03/taskmanager-devops/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Edoto03_taskmanager-devops&metric=alert_status)](https://sonarcloud.io/dashboard?id=Edoto03_taskmanager-devops)

---

## 📋 Project Overview

A Spring Boot REST API for task management demonstrating enterprise-grade DevOps practices from development to production deployment.

### Key Features
- RESTful CRUD API for task management
- PostgreSQL database with automated migrations
- 21 comprehensive unit tests (75% coverage)
- Multi-stage CI/CD pipeline with 8 automated jobs
- Container security scanning
- Code quality analysis with SonarQube
- Kubernetes orchestration with auto-scaling

---

## ✅ DevOps Topics Covered (10/10)

| # | Topic | Implementation | Status |
|---|-------|----------------|--------|
| 1 | **Source Control** | Git/GitHub with protected branches | ✅ |
| 2 | **Branching Strategies** | GitFlow (main/develop/feature) | ✅ |
| 3 | **Building Pipelines** | GitHub Actions (8 automated jobs) | ✅ |
| 4 | **Continuous Integration** | Automated testing + code quality | ✅ |
| 5 | **Continuous Delivery** | Auto Docker build & registry push | ✅ |
| 6 | **Security (SAST)** | Trivy + SpotBugs + SonarQube | ✅ |
| 7 | **Docker** | Multi-stage builds + Docker Compose | ✅ |
| 8 | **Kubernetes** | Deployments + Services + ConfigMaps | ✅ |
| 9 | **Database Changes** | Flyway migrations (V1, V2) | ✅ |
| 10 | **Infrastructure as Code** | Declarative K8s manifests | ✅ |

---

## 🏗️ Architecture
```
┌─────────────────────────────────────────────────────────┐
│                  GitHub Repository                      │
│                                                         │
│  Developer commits → Pull Request → Code Review         │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│            GitHub Actions CI/CD Pipeline                │
│                                                         │
│  ┌──────────┐  ┌──────────┐  ┌───────────┐              │
│  │  Tests   │→ │ Coverage │→ │ SonarQube │              │
│  │ (21)     │  │ (JaCoCo) │  │ Analysis  │              │
│  └──────────┘  └──────────┘  └───────────┘              │
│        │                                                │
│        ▼                                                │
│  ┌──────────┐  ┌──────────┐  ┌───────────┐              │
│  │  Code    │→ │  Build   │→ │   Docker  │              │
│  │ Quality  │  │  (Maven) │  │   Build   │              │
│  └──────────┘  └──────────┘  └─────┬─────┘              │
│                                    │                    │
│                                    ▼                    │
│                              ┌──────────┐               │
│                              │  Trivy   │               │
│                              │ Security │               │
│                              │  Scan    │               │
│                              └────┬─────┘               │
└───────────────────────────────────┼─────────────────────┘
                                    │
                                    ▼
                         ┌──────────────────┐
                         │   Docker Hub     │
                         │ (Image Registry) │
                         └────────┬─────────┘
                                  │
                                  ▼
┌─────────────────────────────────────────────────────────┐
│              Kubernetes Cluster                         │
│                                                         │
│  ┌────────────────┐         ┌──────────────────┐        │
│  │   PostgreSQL   │◄────────┤  Task Manager    │        │
│  │   (1 pod)      │         │  (1 pod)         │        │
│  │   + Storage    │         │  + Auto-healing  │        │
│  └────────────────┘         └──────────────────┘        │
│         ▲                           ▲                   │
│         │                           │                   │
│    ┌────┴────┐                 ┌────┴────┐              │
│    │ Service │                 │ Service │              │
│    │ (5432)  │                 │ (30080) │              │
│    └─────────┘                 └─────────┘              │
└─────────────────────────────────────────────────────────┘
```

---

## 🚀 Quick Start

### Prerequisites
- Java 21
- Maven 3.8+
- Docker Desktop with Kubernetes
- kubectl CLI

### Local Development
```bash
# Clone repository
git clone https://github.com/Edoto03/taskmanager-devops.git
cd taskmanager-devops

# Start PostgreSQL
docker-compose up -d

# Run application
mvn spring-boot:run

# Access API
curl http://localhost:8080/api/tasks
```

### Run Tests
```bash
# All tests
mvn clean test

# With coverage
mvn test jacoco:report
open target/site/jacoco/index.html
```

### Deploy to Kubernetes
```bash
# Deploy everything
kubectl apply -f k8s/

# Verify
kubectl get pods -n taskmanager

# Access API
curl http://localhost:30080/api/tasks
```

---

## 📊 CI/CD Pipeline (8 Jobs)

### Pipeline Jobs

1. **Run Tests** (2 min)
   - Executes 21 unit tests
   - Validates all layers (Controller, Service, Repository)
   - Fails pipeline if any test fails

2. **Code Coverage** (2 min)
   - Generates JaCoCo coverage report
   - Current coverage: **75%**
   - Uploads report as artifact

3. **SonarQube Analysis** (3 min)
   - **NEW!** Code quality + security analysis
   - Detects bugs, vulnerabilities, code smells
   - Quality gate: Must pass to proceed
   - View report: [SonarCloud Dashboard](https://sonarcloud.io/dashboard?id=Edoto03_taskmanager-devops)

4. **Code Quality** (2 min)
   - Checkstyle for code standards
   - SpotBugs for bug detection

5. **Build Application** (3 min)
   - Compiles and packages JAR
   - Uploads artifact for deployment

6. **Build Docker Image** (4 min)
   - Multi-stage Docker build
   - Pushes to Docker Hub (on main branch)
   - Tags: `latest`, `main-<sha>`

7. **Security Scan** (2 min)
   - Trivy scans for vulnerabilities
   - Checks: OS packages + dependencies
   - Severity: Critical & High
   - Generates JSON report

8. **Deployment Status** (1 min)
   - Summary of all jobs
   - Fails if critical jobs fail

**Total Pipeline Time:** ~19 minutes

View live: [GitHub Actions](https://github.com/Edoto03/taskmanager-devops/actions)

---

## 🔒 Security & SAST

### Static Application Security Testing Tools

#### 1. **SonarQube** (Code Quality + Security)
- **What:** Continuous code quality inspection
- **Scans:** 
  - Security vulnerabilities (SQL injection, XSS, etc.)
  - Bugs and code smells
  - Code duplication
  - Technical debt
- **Integration:** Runs on every commit
- **Dashboard:** [View SonarCloud](https://sonarcloud.io/dashboard?id=Edoto03_taskmanager-devops)

**Example findings:**
- Security hotspots: 0
- Bugs: 0
- Code smells: 3 (minor)
- Coverage: 75%
- Duplications: 0%

#### 2. **Trivy** (Container Security)
- **What:** Vulnerability scanner for containers
- **Scans:** 
  - OS packages (Alpine, Ubuntu, etc.)
  - Application dependencies (Maven, npm)
  - Known CVEs from NVD database
- **Integration:** Scans every Docker image before deployment
- **Format:** Table + JSON reports

**Example output:**
```
Total: 12 (HIGH: 2, MEDIUM: 10, LOW: 0)
┌────────────┬──────────────┬──────────┬─────────────┐
│  Library   │ Vulnerability│ Severity │ Fixed Ver   │
├────────────┼──────────────┼──────────┼─────────────┤
│ spring-web │ CVE-2023-xxx │   HIGH   │ 6.0.12      │
└────────────┴──────────────┴──────────┴─────────────┘
```

#### 3. **SpotBugs** (Java Static Analysis)
- **What:** Analyzes Java bytecode for bugs
- **Detects:** 
  - Null pointer dereferences
  - Resource leaks
  - SQL injection risks
  - Hardcoded credentials
- **Integration:** Runs in Code Quality job

#### 4. **Checkstyle** (Code Standards)
- **What:** Enforces Google Java Style Guide
- **Checks:** 
  - Naming conventions
  - Code formatting
  - Import organization
- **Purpose:** Consistency improves security reviews

### Why SAST Matters

1. **Early Detection** - Find vulnerabilities in development
2. **Cost Savings** - 10x cheaper to fix early vs. production
3. **Compliance** - Meets OWASP, PCI-DSS requirements
4. **Quality** - Prevents technical debt

**SAST vs DAST:**

| Aspect | SAST (Our Project) | DAST |
|--------|-------------------|------|
| Method | Analyzes source code | Tests running app |
| When | During development | After deployment |
| Coverage | 100% of code | Exposed interfaces |
| Speed | Fast (minutes) | Slow (hours) |

---

## 📝 API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/tasks` | Get all tasks | - |
| GET | `/api/tasks/{id}` | Get task by ID | - |
| POST | `/api/tasks` | Create new task | `{title, description, status}` |
| PUT | `/api/tasks/{id}` | Update task | `{title, description, status}` |
| DELETE | `/api/tasks/{id}` | Delete task | - |

### Example Requests
```bash
# Get all tasks
curl http://localhost:30080/api/tasks

# Create task
curl -X POST http://localhost:30080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete DevOps Project",
    "description": "Finish all 10 topics",
    "status": "IN_PROGRESS"
  }'

# Update task
curl -X PUT http://localhost:30080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Title",
    "description": "Updated description",
    "status": "COMPLETED"
  }'

# Delete task
curl -X DELETE http://localhost:30080/api/tasks/1
```

---

## 🗄️ Database

### Schema
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

### Flyway Migrations

**V1__Create_tasks_table.sql**
- Creates tasks table
- Adds indexes for performance
- Status: ✅ Applied

**V2__Insert_sample_data.sql**
- Inserts 8 sample tasks
- Different statuses (PENDING, IN_PROGRESS, COMPLETED)
- Status: ✅ Applied

Migrations run automatically on application startup.

---

## ☸️ Kubernetes Deployment

### Resources
```yaml
Namespace:    taskmanager
Deployments:  postgres (1 replica), taskmanager (1 replica)
Services:     postgres-service (ClusterIP), taskmanager-service (NodePort)
ConfigMap:    taskmanager-config (app settings)
Secret:       taskmanager-secret (DB credentials)
PVC:          postgres-pvc (1Gi persistent storage)
```

### Key Features

- **Auto-healing:** Pods restart on failure
- **Rolling updates:** Zero-downtime deployments
- **Resource limits:** CPU & memory constraints
- **Health checks:** Liveness & readiness probes
- **Init containers:** Wait for database before starting
- **Persistent storage:** Database survives pod restarts

### Commands
```bash
# View all resources
kubectl get all -n taskmanager

# Check logs
kubectl logs -n taskmanager deployment/taskmanager -f

# Scale application
kubectl scale deployment taskmanager --replicas=3 -n taskmanager

# Access database
kubectl exec -it -n taskmanager deployment/postgres -- \
  psql -U postgres -d taskmanager

# Port forward (alternative access)
kubectl port-forward -n taskmanager service/taskmanager-service 8080:8080
```

---

## 🧪 Testing

### Test Coverage
```
Controller Layer:  7 tests  (85% coverage)
Service Layer:     7 tests  (80% coverage)
Repository Layer:  7 tests  (70% coverage)
─────────────────────────────────────────
Total:            21 tests  (75% coverage)
```

### Technologies

- **JUnit 5** - Test framework
- **Mockito** - Mocking dependencies
- **MockMvc** - Testing REST endpoints
- **AssertJ** - Fluent assertions
- **H2** - In-memory test database

---

## 📦 Technologies

### Backend
- Java 21
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL 15
- Flyway 9.22

### DevOps
- Git & GitHub
- GitHub Actions
- Docker & Docker Compose
- Kubernetes
- Maven 3.8

### Quality & Security
- JUnit 5 & Mockito
- JaCoCo (Coverage)
- **SonarQube (NEW!)**
- Checkstyle
- SpotBugs
- Trivy

---

## 🎯 Lessons Learned

### What Went Well ✅
- Automated testing caught bugs early
- Docker simplified deployment
- Kubernetes provided resilience
- SonarQube improved code quality

### Challenges Faced 🔧
- Flyway migration naming conventions
- Kubernetes persistent volume setup
- GitHub Actions secret management
- SonarCloud integration

### Future Improvements 🚀
- Add integration tests
- Implement monitoring (Prometheus/Grafana)
- Add log aggregation (ELK)
- Implement blue-green deployment
- Add Helm charts

---

## 👨‍💻 Author

- GitHub: [@Edoto03](https://github.com/Edoto03)
- Project: [taskmanager-devops](https://github.com/Edoto03/taskmanager-devops)

---

## 📄 License

This project is created for educational purposes as part of a DevOps course final project.

---

## 🙏 Acknowledgments

- DevOps course instructors
- Spring Boot documentation
- Kubernetes documentation
- SonarQube community
- OWASP security guidelines

---

**Last Updated:** January 2026
