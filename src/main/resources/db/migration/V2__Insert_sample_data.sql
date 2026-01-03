-- V2: Insert sample data
INSERT INTO tasks (title, description, status, created_at, updated_at) VALUES
('Setup Development Environment', 'Install Java 21, Maven, Docker, and Kubernetes', 'COMPLETED', NOW() - INTERVAL '7 days', NOW() - INTERVAL '6 days'),
('Create Spring Boot Application', 'Initialize project with dependencies', 'COMPLETED', NOW() - INTERVAL '6 days', NOW() - INTERVAL '5 days'),
('Implement CRUD Operations', 'Create REST API endpoints', 'COMPLETED', NOW() - INTERVAL '5 days', NOW() - INTERVAL '4 days'),
('Write Unit Tests', 'Create 21 comprehensive tests', 'COMPLETED', NOW() - INTERVAL '4 days', NOW() - INTERVAL '3 days'),
('Setup CI/CD Pipeline', 'Configure GitHub Actions', 'COMPLETED', NOW() - INTERVAL '3 days', NOW() - INTERVAL '2 days'),
('Configure Docker', 'Create Dockerfile and compose', 'COMPLETED', NOW() - INTERVAL '2 days', NOW() - INTERVAL '1 day'),
('Setup Kubernetes', 'Create K8s manifests', 'IN_PROGRESS', NOW() - INTERVAL '1 day', NOW()),
('Deploy to Production', 'Final K8s deployment', 'PENDING', NOW(), NOW());