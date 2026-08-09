# Student Management System – Backend

A RESTful backend application for managing students, courses, and users.
The system is developed using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, and **PostgreSQL (Supabase)**.

---

## 🚀 Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL
* Supabase
* Maven
* REST API
* BCrypt Password Encryption

---

## 📁 Project Structure

```text
studentmanagement/
│
├── config/
│   └── SecurityConfig.java
│
├── controller/
│   ├── AuthController.java
│   ├── StudentController.java
│   └── CourseController.java
│
├── dto/
│   ├── request/
│   │   ├── LoginRequest.java
│   │   ├── StudentRequest.java
│   │   └── CourseRequest.java
│   │
│   └── response/
│       ├── LoginResponse.java
│       ├── StudentResponse.java
│       └── CourseResponse.java
│
├── entity/
│   ├── User.java
│   ├── Student.java
│   └── Course.java
│
├── repository/
│   ├── UserRepository.java
│   ├── StudentRepository.java
│   └── CourseRepository.java
│
├── service/
│   ├── UserService.java
│   ├── StudentService.java
│   └── CourseService.java
│
├── security/
│   ├── JwtService.java
│   └── JwtAuthenticationFilter.java
│
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── EmailAlreadyExistsException.java
│   └── InvalidCredentialsException.java
│
└── StudentmanagementApplication.java
```

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
                Client
           (Frontend / Postman)
                    │
                    ▼
              Controller
                    │
                    ▼
                Service
                    │
                    ▼
              Repository
                    │
                    ▼
             PostgreSQL
                Database
```

### Layer Responsibilities

| Layer      | Responsibility                               |
| ---------- | -------------------------------------------- |
| Controller | Handles HTTP requests and responses          |
| Service    | Contains business logic                      |
| Repository | Communicates with the database               |
| Entity     | Represents database tables                   |
| DTO        | Transfers data between client and server     |
| Security   | Handles JWT authentication and authorization |
| Exception  | Centralized error handling                   |
| Config     | Application and security configuration       |

---

## 🔐 Authentication & Authorization

The application uses **Spring Security with JWT authentication**.

### Authentication Flow

```text
User
 │
 ▼
Login
 │
 ▼
AuthController
 │
 ▼
UserService
 │
 ▼
Validate Credentials
 │
 ▼
Generate JWT
 │
 ▼
Return Token
```

For protected APIs:

```text
Client
  │
  │ Authorization: Bearer <JWT>
  ▼
JWT Authentication Filter
  │
  ▼
Validate Token
  │
  ├── Invalid → 401 Unauthorized
  │
  └── Valid
       │
       ▼
   Controller
       │
       ▼
     Service
       │
       ▼
   Repository
       │
       ▼
    Database
```

Passwords are securely encrypted using **BCrypt** before being stored in the database.

---

## 👤 User Management

### Register

```http
POST /api/auth/register
```

Example request:

```json
{
  "name": "Admin User",
  "email": "admin@gmail.com",
  "password": "123456",
  "role": "ADMIN",
  "contactNum": "0771234567"
}
```

---

### Login

```http
POST /api/auth/login
```

Example request:

```json
{
  "email": "admin@gmail.com",
  "password": "123456"
}
```

Example response:

```json
{
  "token": "JWT_TOKEN",
  "role": "ADMIN",
  "email": "admin@gmail.com"
}
```

The returned JWT token must be included when accessing protected endpoints.

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# 👨‍🎓 Student Management

The Student API provides CRUD operations for managing students.

### Create Student

```http
POST /api/students
```

### Get All Students

```http
GET /api/students
```

### Get Student by ID

```http
GET /api/students/{id}
```

### Update Student

```http
PUT /api/students/{id}
```

### Delete Student

```http
DELETE /api/students/{id}
```

---

## 📚 Course Management

The Course API provides CRUD operations for managing courses.

### Create Course

```http
POST /api/courses
```

### Get All Courses

```http
GET /api/courses
```

### Get Course by ID

```http
GET /api/courses/{id}
```

### Update Course

```http
PUT /api/courses/{id}
```

### Delete Course

```http
DELETE /api/courses/{id}
```

---

## 🔗 Entity Relationship

A course can have multiple students.

```text
        Course
          │
          │ 1
          │
          │
          │ *
          ▼
       Student
```

### Course

```text
id
name
description
fee
duration
```

### Student

```text
id
name
email
age
course
```

### User

```text
id
name
email
password
role
contactNum
```

---

## 📦 DTO Architecture

Request and Response DTOs are separated to improve security and maintainability.

```text
Client
  │
  ▼
Request DTO
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Entity
  │
  ▼
Database
```

For responses:

```text
Database
   │
   ▼
Entity
   │
   ▼
Service
   │
   ▼
Response DTO
   │
   ▼
Client
```

This prevents database entities from being directly exposed through the REST API.

---

## ⚠️ Exception Handling

The application uses centralized exception handling through:

```text
GlobalExceptionHandler
```

Common responses include:

| Status | Meaning               |
| ------ | --------------------- |
| 200    | Successful request    |
| 201    | Resource created      |
| 400    | Bad request           |
| 401    | Unauthorized          |
| 404    | Resource not found    |
| 409    | Conflict              |
| 500    | Internal server error |

Custom exceptions include:

* `ResourceNotFoundException`
* `EmailAlreadyExistsException`
* `InvalidCredentialsException`

---

# ⚙️ Setup & Installation

## 1. Clone the Repository

```bash
git clone <repository-url>
```

## 2. Navigate to the Project

```bash
cd studentmanagement
```

## 3. Configure Database

Update your database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://<host>:<port>/<database>
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

For Supabase PostgreSQL, use the database connection details provided by Supabase.

---

## 4. Configure JWT Secret

Add your JWT configuration securely through environment variables or application configuration.

Example:

```properties
jwt.secret=${JWT_SECRET}
```

> Do not commit real database passwords, JWT secrets, API keys, or other sensitive credentials to GitHub.

---

## 5. Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or run:

```text
StudentmanagementApplication.java
```

from your IDE.

---

## 🌐 API Base URL

When running locally:

```text
http://localhost:8080
```

Example:

```text
http://localhost:8080/api/auth/login
```

---

## 🧪 Testing

The REST APIs can be tested using:

* Postman
* Frontend application
* Browser for GET endpoints

### Recommended Testing Flow

```text
1. Register User
       ↓
2. Login
       ↓
3. Receive JWT Token
       ↓
4. Add JWT to Authorization Header
       ↓
5. Access Student/Course APIs
```

---

## 🔑 Authorization Header

For protected endpoints:

```http
Authorization: Bearer <JWT_TOKEN>
```

Example:

```text
Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## ✨ Main Features

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Role-Based Authorization
* Student CRUD Operations
* Course CRUD Operations
* Student–Course Relationship
* DTO-based Request/Response Handling
* PostgreSQL Database
* Supabase Database Support
* Global Exception Handling
* RESTful API Architecture
* Layered Backend Architecture

---

## 🛡️ Security

The application follows security best practices including:

* JWT-based authentication
* BCrypt password hashing
* Protected REST endpoints
* Authentication filter
* Centralized exception handling
* DTO separation
* Environment-based secret configuration

---

## 👩‍💻 Development

### Create a Feature Branch

```bash
git checkout -b feature/<feature-name>
```

### Commit Changes

```bash
git add .
git commit -m "feat: complete backend feature implementation"
```

### Push Feature Branch

```bash
git push origin feature/<feature-name>
```

Then create a Pull Request to merge the feature branch into the main development branch.

---

## 📄 License

This project is developed for educational and internship/project purposes.
