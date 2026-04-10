# 🚀 Job Tracker API

A **Spring Boot backend application** to track job applications and automate follow-ups using a **scheduler-based reminder system with email notifications**.

---

# 🎯 Project Overview

Job Tracker helps users:

* Track job applications
* Manage application status
* Set reminders for follow-ups & interviews
* Receive automated email notifications

👉 The goal is to make job tracking **structured, automated, and reliable**

---

# 🧩 Features Implemented

## ✅ Stage 1 — Job Tracking (Core CRUD)

* Create Job
* Update Job
* Delete Job
* Get Job(s)

---

## ✅ Stage 2 — Status Management

* Track job status (APPLIED, INTERVIEW, OFFER, REJECTED)
* Update status dynamically

---

## ✅ Stage 3 — Notification API

* Base notification structure
* Extendable for multiple channels

---

## 🔥 Stage 4 — Reminder System (USP)

* Create reminders for jobs
* Types:

  * FOLLOW_UP
  * INTERVIEW
* Status:

  * PENDING
  * SENT

### ⏰ Scheduler

* Runs every minute
* Fetches due reminders
* Triggers notification
* Updates status to SENT

---

## 📧 Stage 5 — Email Notification (Real-world feature)

* Sends email when reminder is triggered
* Uses **Gmail SMTP**
* Secure authentication via App Password

---

# 🏗️ Tech Stack

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **PostgreSQL**
* **Spring Scheduler**
* **Spring Mail (SMTP)**
* **Lombok**
* **Swagger (OpenAPI)**

---

# 📂 Project Structure

```text
com.jobtracker
│
├── controller     # REST APIs
├── service        # Business logic
├── repository     # Database layer
├── entity         # JPA entities
├── dto            # Request/Response objects
├── scheduler      # Background jobs
├── config         # Configurations (mail, etc.)
├── scheduler      # automate jobs(reminder,email etc.)


```

---

# ⚙️ Setup & Run

## 1️⃣ Clone Repo

```bash
git clone https://github.com/Yaamin1921/job-tracker.git
cd job-tracker
```

---

## 2️⃣ Configure Database

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/job_tracker
    username: your_username
    password: your_password
```

---

## 3️⃣ Configure Email (SMTP)

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

---

## 4️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 5️⃣ Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Sample API (Create Reminder)

```json
{
  "jobId": 101,
  "reminderTime": "2026-04-10T17:45:54Z",
  "type": "FOLLOW_UP",
  "email": "your-email@gmail.com"
}
```

---

# 🔄 Reminder Flow

```text
User creates reminder
        ↓
Stored as PENDING
        ↓
Scheduler runs every minute
        ↓
Due reminder found
        ↓
Email sent
        ↓
Status updated to SENT
```

---

# 💡 Key Highlights

* ⏰ Scheduler-based automation
* 📧 Real email notifications
* 🧠 Clean architecture (Controller → Service → Repo)
* 🔐 Secure email via App Password
* 📦 DTO-based request handling
* 📊 Production-ready design

---

# 🚀 Future Enhancements

* Email templates (HTML)
* Retry mechanism for failed emails
* SMS / WhatsApp notifications
* User authentication (JWT)
* Dashboard & analytics
* Kafka for scalable notifications

---

# 🧠 Learning Outcomes

* Scheduler in Spring Boot
* Email integration using SMTP
* DTO vs Entity best practices
* Enum handling in APIs
* Timezone handling (UTC vs IST)
* Clean architecture design

---

# 👨‍💻 Author

**Mohd Yaamin**

---

# ⭐ Contribution

Feel free to fork, raise issues, or contribute!

---

# 🔥 Interview One-Liner

> Built a scheduler-driven job tracking system with automated email reminders to prevent missed follow-ups.

---
