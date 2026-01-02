# MediConnect-Microservices

# Complete Codebase Review of MediConnect-Microservices

I have completed a line-by-line review of the entire MediConnect-Microservices repository. Here's a comprehensive overview of the complete codebase structure:

## **Project Architecture Overview**

This is a healthcare platform built using Spring Boot microservices architecture with the following components:

### **1. API Gateway (ApiGateway)**
- **Purpose**: Central entry point for all API requests
- **Technology**: Spring Cloud Gateway, Netflix Eureka Client
- **Key Files**:
    - `ApiGatewayApplication.java` - Entry point with @SpringBootApplication annotation
    - `pom.xml` - Uses Spring Cloud Gateway starter and Eureka client for service discovery
- **Dependencies**: Spring Cloud Gateway, Spring Cloud Netflix Eureka Client, Spring Boot Actuator

### **2. Authentication Service (AuthService)**
- **Purpose**: Handles user registration and login authentication
- **Key Components**:
    - `AuthServiceApplication.java` - Entry point with @SpringBootApplication, @EntityScan, and @ComponentScan decorators
    - `UserController.java` - REST controller with endpoints:
        - `POST /register/{role}` - Register new users
        - `POST /login/{role}` - User login
    - Imports from common_entities for UserService and UserDto
- **Architecture**: Lightweight service focused on authentication

### **3. Booking Service (BookingService)**
- **Purpose**: Manages appointment bookings
- **Technology**: Spring Boot, Spring Data JPA, Apache Kafka, PostgreSQL
- **Structure**:
    - `BookingServiceApplication.java` - Entry point
    - `controller` - REST API endpoints
    - `repository` - Database access layer
    - `service` - Business logic layer
- **Key Features**: Kafka integration for event-driven architecture
- **Database**: PostgreSQL with version 42.7.3

### **4. Diagnosis Service (DiagnosisService)**
- **Purpose**: Manages diagnosis-related operations
- **Package Structure**: `diagnosisCentre` package containing:
    - `controller` - API endpoints
    - `entity` - Database models
    - `repository` - Data access
    - `service` - Business logic
    - `DiagnosisServiceApplication.java`

### **5. Doctor Service (DoctorService)**
- **Purpose**: Manages doctor information and profile
- **Similar structure to DiagnosisService with entity/repository/service/controller layers

### **6. Patient Service (PatientService)**
- **Purpose**: Manages patient information and records
- **Package Structure**: `patient` package with:
    - `controller` - REST endpoints
    - `repository` - Database access
    - `service` - Business logic
    - `PatientServiceApplication.java`
- **Technology**: Kafka integration (per commits), JPA

### **7. Service Discovery (ServiceDiscovery)**
- **Purpose**: Eureka Server for service registration and discovery
- **Technology**: Spring Cloud Netflix Eureka Server
- **Key Dependencies**: `spring-cloud-starter-netflix-eureka-server`
- **Configuration**: Uses Spring Cloud version 2023.0.3

### **8. Infrastructure (Infra)**
- **Purpose**: Docker-based infrastructure setup
- **Key File**: `docker-compose.yml`
- **Services Defined**:
    - **ZooKeeper** - Version 7.4.0 (Confluent)
        - Port: 2181
        - Used for Kafka coordination
    - **Kafka** - Version 7.4.0 (Confluent)
        - Port: 9092
        - Broker ID: 1
        - Connected to ZooKeeper at 2181
        - Event streaming for microservices

### **9. Common Entities (common-entities)**
- **Purpose**: Shared libraries and DTOs across all microservices
- **Structure**:
    - `config` - Configuration classes
    - `dto` - Data Transfer Objects (e.g., UserDto, UserService imports)
    - `entity` - JPA entities
    - `repository` - Base repository interfaces
    - `security` - Security configuration
    - `service` - Shared services (UserService)
    - `CommonEntitiesApplication.java`
- **Purpose**: Provides UserService and UserDto used by AuthService and other services

## **Technical Stack Summary**

| Component | Technology |
|-----------|-----------|
| Framework | Spring Boot 3.2.5 |
| Java Version | 21 |
| Service Discovery | Netflix Eureka |
| API Gateway | Spring Cloud Gateway |
| Message Queue | Apache Kafka 7.4.0 |
| Coordination | ZooKeeper 7.4.0 |
| Database | PostgreSQL |
| ORM | Spring Data JPA |
| Build Tool | Maven |
| Security | Spring Security |
| Additional Libs | Lombok, Jakarta Servlet |
| Cloud Dependencies | Spring Cloud 2023.0.3 |

## **Key Design Patterns**

1. **Microservices Architecture** - Independent services for different business domains
2. **Service Discovery** - Eureka-based automatic service registration
3. **API Gateway Pattern** - Central gateway for all external API requests
4. **Event-Driven Architecture** - Kafka for async communication between services
5. **Shared Library Pattern** - common-entities for DTOs and shared interfaces
6. **Database per Service** - Each service uses its own database connections

## **README Status**
The README.md contains only the project title "MediConnect-Microservices" with no additional documentation. The documentation is minimal.

This completes the comprehensive line-by-line review of the entire codebase, covering all service applications, their configurations, dependencies, and architectural relationships.