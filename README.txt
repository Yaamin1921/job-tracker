# 🚀 Job Tracker API

A **Spring Boot backend** for managing job applications, tracking application activity, scheduling follow-ups, and processing notifications asynchronously.

## ✨ Features

* Job application CRUD and status management
* Application status tracking: `APPLIED`, `INTERVIEW`, `HR_REPLIED`, `OFFERED`, `REJECTED`, `NO_RESPONSE`, `SAVED`
* Activity timeline for job events
* Notes and interaction tracking
* Reminder management for follow-ups and interviews
* Scheduled reminder processing
* Email notifications using Gmail SMTP
* Dashboard and application analytics
* Asynchronous event processing with RabbitMQ
* Event-driven job, status, notes, and email workflows
* Idempotent event processing to prevent duplicate processing
* Processing state tracking for email events
* DTO-based API design and validation
* Global exception handling
* Swagger / OpenAPI documentation

## 🏗️ Architecture


                    REST API
                       │
                       ▼
              ┌─────────────────┐
              │   Controllers   │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │    Services     │
              └───────┬─────────┘
                      │
             ┌────────┴─────────┐
             ▼                  ▼
       PostgreSQL          RabbitMQ
                              │
                 ┌────────────┴────────────┐
                 ▼                         ▼
          Event Consumers            Email Consumer
                 │                         │
                 ▼                         ▼
          Activity/Status             Email Service
            Processing


## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot 3.2**
* **Spring Web**
* **Spring Data JPA / Hibernate**
* **PostgreSQL**
* **RabbitMQ / Spring AMQP**
* **Spring Scheduler**
* **Spring Mail / Gmail SMTP**
* **Bean Validation**
* **Lombok**
* **ModelMapper**
* **Swagger / OpenAPI**
* **JUnit / Spring Boot Test**
* **H2** for testing

## 📂 Project Structure

com.jobtracker
├── config       # Application & RabbitMQ configuration
├── controller   # REST APIs
├── consumer     # RabbitMQ consumers
├── dto          # Request/response DTOs
├── entity       # JPA entities & enums
├── event        # Domain events
├── exception    # Exception handling
├── publisher    # Event publishers
├── repository   # Database access
├── scheduler    # Scheduled background jobs
├── service      # Business logic
└── util         # Utility classes


## 🔄 Event Processing

Job API
   │
   ▼
Database
   │
   └── Publish Event
          │
          ▼
       RabbitMQ
          │
     ┌────┴─────┐
     ▼          ▼
 Activity     Email
 Consumer     Consumer
     │          │
     ▼          ▼
 Activity     Email
 Tracking     Service

Events are identified using unique event IDs and processed with **idempotency checks** to prevent duplicate processing.

## ⏰ Reminder Flow


Create Reminder
      ↓
    PENDING
      ↓
Scheduler checks due reminders
      ↓
Publish / trigger notification
      ↓
Email processing
      ↓
     SENT
```

## ⚙️ Setup

### 1. Clone
git clone https://github.com/Yaamin1921/job-tracker.git
cd job-tracker

### 2. Configure PostgreSQL

Update your `application-local.yml` with your PostgreSQL connection.

### 3. Configure RabbitMQ

Configure RabbitMQ connection details in `application-local.yml`.

### 4. Configure Gmail SMTP

Configure Gmail SMTP credentials using an **App Password**.

### 5. Run

```bash
mvn spring-boot:run
```

### 6. API Documentation

```text
http://localhost:8080/swagger-ui/index.html
```

## 🎯 Backend Concepts Demonstrated

* REST API design
* Layered architecture
* JPA & relational persistence
* DTO mapping and validation
* Scheduled background processing
* Event-driven architecture
* Message queues with RabbitMQ
* Asynchronous processing
* Idempotency
* Event lifecycle/state management
* Email integration
* Exception handling
* API documentation
* Unit/integration testing

## 👨‍💻 Author

**Mohd Yaamin**


