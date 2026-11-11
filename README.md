
# 🛒 E-Commerce Microservices

A scalable and modular **E-Commerce Backend Application** built using Java, Spring Boot, and Microservices Architecture.

The project follows a distributed architecture where individual business capabilities are developed as independent microservices. It uses Spring Cloud Gateway for centralized API routing, Netflix Eureka for service discovery, Apache Kafka for asynchronous communication, and Micrometer with Zipkin for distributed tracing and observability.

## 🚀 Project Overview

This application demonstrates how a real-world e-commerce backend can be designed using microservices to improve scalability, maintainability, and independent deployment.

Each microservice handles a specific business responsibility and communicates with other services through REST APIs and asynchronous messaging where required.

### Key Highlights

- Microservices-based backend architecture.
- Centralized API routing using Spring Cloud Gateway.
- Service registration and discovery using Netflix Eureka.
- JWT-based authentication and role-based authorization.
- Asynchronous communication using Apache Kafka.
- Distributed request tracing using Micrometer and Zipkin.
- RESTful API development using Spring Boot.
- Centralized request filtering and authorization.
- Independent business services for e-commerce operations.

## 🏗️ Architecture

The application consists of the following components:

```text
                    CLIENT / POSTMAN
                           |
                           |
                    API GATEWAY
                      Port: 8087
                           |
              JWT Authentication & RBAC
                           |
                           |
                  EUREKA DISCOVERY
                    Port: 8761
                           |
        +------------------+------------------+
        |                  |                  |
        v                  v                  v
   USER SERVICE       PRODUCT SERVICE    INVENTORY SERVICE
        |                  |                  |
        +------------------+------------------+
                           |
                           v
                     ORDER SERVICE
                           |
                           v
                    PAYMENT SERVICE
                           |
                           v
                  NOTIFICATION SERVICE
                           |
                           v
                     KAFKA BROKER

             MICROMETER + ZIPKIN
             Distributed Tracing
```

*Note: The diagram represents the major components and their logical relationships. Actual service-to-service communication depends on the implemented workflows.*

## 📦 Microservices

| Service | Responsibility |
|---|---|
| **API Gateway** | Acts as the single entry point for client requests, routing requests to the appropriate microservice. |
| **Eureka Server** | Provides service registration and discovery, allowing microservices to locate one another dynamically. |
| **User Service** | Handles user registration, authentication, and user-related operations. |
| **Product Service** | Manages product information and product-related operations. |
| **Inventory Service** | Handles inventory and stock-related operations. |
| **Order Service** | Manages customer orders and order-related business logic. |
| **Payment Service** | Handles payment processing and payment status management. |
| **Notification Service** | Responsible for handling notification-related operations. |
| **Kafka Infrastructure** | Provides messaging infrastructure for asynchronous communication between services. |

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| Programming Language | Java |
| Backend Framework | Spring Boot |
| Architecture | Microservices |
| API Gateway | Spring Cloud Gateway |
| Service Discovery | Netflix Eureka |
| Inter-service Communication | REST APIs |
| Messaging | Apache Kafka |
| Authentication | Spring Security, JWT |
| ORM | Spring Data JPA, Hibernate |
| Database | MySQL |
| Monitoring & Observability | Micrometer |
| Distributed Tracing | Zipkin |
| Build Tool | Maven |
| API Testing | Postman |
| IDE | Eclipse / IntelliJ IDEA |
| Version Control | Git & GitHub |

## 🔐 API Gateway & Security

Spring Cloud Gateway acts as the centralized entry point for incoming client requests.

### Implemented Security Concepts

- JWT-based authentication.
- Token validation at the API Gateway.
- Role-based access control (RBAC).
- Custom Global Filters.
- Custom Route Filters.
- Public endpoint handling for registration and login.
- Forwarding authenticated user information to downstream services through request headers.

### Request Flow

1. Client sends an HTTP request to the API Gateway.
2. Gateway identifies the requested route.
3. JWT filter validates the authentication token for protected endpoints.
4. Role-based authorization checks whether the user has permission to access the requested API.
5. Gateway forwards the request to the appropriate microservice.
6. The microservice processes the request and returns a response.

## 🔄 Asynchronous Communication

Apache Kafka is included as the messaging infrastructure for asynchronous communication.

Kafka enables services to exchange messages without requiring every interaction to happen through synchronous REST calls.

This architecture helps reduce direct service dependencies and supports event-driven workflows.

## 📊 Monitoring & Distributed Tracing

The project uses **Micrometer and Zipkin** to support distributed tracing and observability across microservices.

### Micrometer

Micrometer provides instrumentation and tracing integration for Spring Boot applications.

It helps capture application telemetry and propagate trace context across service boundaries.

### Zipkin

Zipkin is used to visualize distributed traces and understand request execution across multiple microservices.

### Benefits

- Track requests across multiple services.
- Identify service-level latency.
- Analyze request execution flow.
- Troubleshoot failures in distributed workflows.
- Understand where delays occur during service-to-service communication.

## 🔗 API Gateway Routes

The API Gateway currently exposes routes for the following services:

| Service | Route Path |
|---|---|
| User Service | `/api/users/**` |
| Product Service | `/api/products/**` |
| Category APIs | `/api/categories/**` |
| Inventory Service | `/api/inventory/**` |
| Order Service | `/api/orders/**` |

**Gateway Base URL:**

```text
http://localhost:8087
```

**Examples:**

```http
POST http://localhost:8087/api/users/register
POST http://localhost:8087/api/users/login
```

Protected endpoints require a valid JWT token in the Authorization header:

```http
Authorization: Bearer <your-jwt-token>
```

## ⚙️ Getting Started

### Prerequisites

Make sure the following tools are installed:

- Java 17 or compatible project JDK.
- Maven.
- MySQL.
- Apache Kafka.
- Git.
- Postman.
- Zipkin.

### Clone the Repository

```bash
git clone https://github.com/manojk-saw/manojk-saw-springboot-ecommerce-microservices.git
```

Navigate to the project directory:

```bash
cd manojk-saw-springboot-ecommerce-microservices
```

### Database Configuration

Configure your MySQL database connection in the respective microservice's `application.properties` file.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Update the database name and credentials according to your local environment.

### Start the Application

Start the infrastructure and services in the following order:

1. MySQL
2. Eureka Server
3. Kafka infrastructure
4. User Service
5. Product Service
6. Inventory Service
7. Order Service
8. Payment Service
9. Notification Service
10. API Gateway

Make sure the required configuration is available before starting each service.

### Access Eureka Dashboard

```text
http://localhost:8761
```

Use the Eureka dashboard to verify service registration and instance availability.

## 🧪 API Testing

Postman can be used to test the application's REST APIs.

The recommended testing sequence is:

1. Register a new user.
2. Authenticate the user and obtain a JWT token.
3. Use the token to access protected APIs.
4. Test product and category operations.
5. Test inventory operations.
6. Create and manage orders.
7. Test payment-related operations.
8. Verify notification workflows.

## 📁 Repository Structure

```text
manojk-saw-springboot-ecommerce-microservices/
│
├── api-gateway/
│
├── eureka-server/
│
├── user-service/
│
├── product-service/
│
├── inventory-service/
│
├── order-service/
│
├── payment-service/
│
├── notification-service/
│
├── kafka-infra/
│
└── README.md
```

## 🎯 Learning Objectives

This project demonstrates practical implementation of:

- Designing and developing microservices.
- Implementing API Gateway patterns.
- Service registration and discovery.
- JWT authentication and role-based authorization.
- Inter-service communication.
- Event-driven architecture fundamentals.
- Distributed tracing and observability.
- Building scalable backend applications using Spring Boot.

## 👨‍💻 Author

**Manoj Saw**

Java Backend Developer

GitHub: [manojk-saw](https://github.com/manojk-saw)

---
