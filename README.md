# 🧺 Laundry Pickup & Delivery Management System - Backend

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=flat-square&logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?style=flat-square&logo=mysql)](https://www.mysql.com/)
[![Last Commit](https://img.shields.io/github/last-commit/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=flat-square)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)
[![Repo Size](https://img.shields.io/github/repo-size/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=flat-square)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)
[![Postman Tests](https://img.shields.io/badge/Postman-100%25_Tested-FF6C37?style=flat-square&logo=postman)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)

> A complete enterprise-grade Spring Boot backend for laundry pickup, processing, and delivery management with JWT authentication, role-based access control, and real-time order tracking.

**📸 [View Complete Postman Test Suite →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

## 🎉 Latest Updates (Feb 14, 2026)

### ✨ NEW Features Released Today!

- ✅ **Employee Management Controller** - Complete CRUD operations for employee management
- ✅ **Employee Order Status Tracking** - Real-time order status visibility for employees
- ✅ **Dynamic Employee Assignment** - PUT endpoint for flexible employee-order assignment
- ✅ **Employee Directory** - GET all employees endpoint
- ✅ **Admin Employee Dashboard** - Enhanced admin panel with employee management
- ✅ **Complete Postman Test Suite** - 100% API coverage with screenshots

**Contributors:** @minidu1, @KawyaDissanayaka, @DarshanaChinthaka

---

## 📑 Table of Contents

- [Features Overview](#-features-overview)
- [Latest Features](#-new-features-february-2026)
- [Tech Stack](#️-tech-stack)
- [Quick Start](#-quick-start)
- [API Documentation](#-api-documentation)
- [Postman Testing Suite](#-postman-testing-suite)
- [Architecture](#️-architecture)
- [Database Schema](#-database-schema)
- [Security](#-security)
- [Business Logic](#-business-logic)
- [Error Handling](#-error-handling)
- [Configuration](#-configuration)
- [Contributors](#-project-insights--contributors)

---

## ✨ Features Overview

### 🔐 Authentication & Authorization
- **JWT-Based Authentication** - Secure token-based authentication system
- **Role-Based Access Control (RBAC)** - 4 distinct user roles (Admin, Customer, Employee, Rider)
- **Password Encryption** - BCrypt hashing for secure password storage
- **Token Management** - 24-hour token expiration with refresh capability
- **User Registration** - Self-service customer registration
- **Login/Logout** - Secure session management

### 👥 User Management
- **Multi-Role System**
  - **Admin** - Full system access and user management
  - **Customer** - Order placement and tracking
  - **Employee** - Order processing and status updates
  - **Rider** - Pickup and delivery management
- **Profile Management** - Users can update their profile information
- **User Creation** - Admins can create employee and rider accounts
- **Default User Seeding** - Automatic creation of test users on first startup
- **Employee Directory** - ⭐ NEW: View all employees in the system
- **Employee Assignment Management** - ⭐ NEW: Dynamic employee assignment to orders

### 📦 Order Management System
- **Order Creation** - Customers can place laundry orders with multiple items
- **Order Tracking** - Real-time order status updates
- **Order Assignment** - Admin assigns orders to employees and riders
- **Employee Order Status** - ⭐ NEW: Employees can view order status in real-time
- **Order Status Flow**
  1. `PLACED` - Customer creates order
  2. `ASSIGNED` - Admin assigns employee/rider
  3. `AT_LAUNDRY` - Rider confirms pickup
  4. `PROCESSING` - Employee processes laundry
  5. `READY` - Processing completed
  6. `DELIVERED` - Rider delivers order
  7. `COMPLETED` - Order finalized
  8. `CANCELLED` - Order cancelled
- **Order Items** - Support for multiple items per order with individual pricing
- **Order History** - Complete order history for customers
- **Progress Notes** - Employees can add progress updates

### 🚚 Delivery Management
- **Rider Assignment** - Admins assign riders to orders
- **Pickup Confirmation** - Riders confirm pickup from customer
- **Delivery Confirmation** - Riders confirm delivery to customer
- **Pickup & Delivery Dates** - Automatic tracking of pickup and delivery timestamps
- **Rider Dashboard** - Statistics and assigned orders view
- **Commission Calculation** - 5% commission on completed deliveries

### 📊 Analytics & Reporting
- **Admin Analytics**
  - Total orders count
  - Total revenue
  - Orders by status breakdown
  - User statistics
  - ⭐ NEW: Employee performance metrics
- **Customer Dashboard**
  - Total orders placed
  - Order status distribution
  - Total spending
- **Rider Dashboard**
  - Completed deliveries count
  - Total earnings/commission
  - Pending deliveries
- **Employee Dashboard** - ⭐ NEW
  - Assigned orders count
  - Processing status overview
  - Completed tasks count

### ⚙️ Employee Processing
- **Assigned Orders View** - Employees see only their assigned orders
- **Status Updates** - Update order processing status
- **Progress Tracking** - Add detailed progress notes
- **Workload Management** - Track orders in progress
- **Employee Status Monitoring** - ⭐ NEW: Real-time order status tracking
- **Order Assignment Updates** - ⭐ NEW: Update employee assignments dynamically

### 🛡️ Security Features
- **JWT Token Authentication**
- **Role-Based Access Control**
- **Password Encryption (BCrypt)**
- **CORS Configuration** - Enabled for frontend integration
- **Stateless Session Management**
- **Request Authorization** - Endpoint-level security
- **Data Validation** - Input validation on all endpoints

### 🗄️ Database Features
- **MySQL Integration** - Robust relational database
- **JPA/Hibernate ORM** - Object-relational mapping
- **Automatic Schema Creation** - Database tables created automatically
- **Foreign Key Relationships** - Referential integrity
- **Timestamp Tracking** - Created and updated timestamps
- **Transaction Management** - ACID compliance

---

## 🆕 NEW Features (February 2026)

### Employee Management System

#### 1. Get Employee Order Status
**Endpoint:** `GET /api/employee/orders/status`
```http
GET /api/employee/orders/status
Authorization: Bearer {token}
```

**Response:**
```json
{
  "employeeId": 3,
  "assignedOrders": 5,
  "processingOrders": 2,
  "completedOrders": 3,
  "orders": [
    {
      "id": 10,
      "status": "PROCESSING",
      "customerName": "John Doe",
      "itemCount": 5,
      "assignedDate": "2026-02-14T10:00:00"
    }
  ]
}
```

**📸 [View Postman Test →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. Update Employee Assignment
**Endpoint:** `PUT /api/admin/orders/{orderId}/assign-employee`
```http
PUT /api/admin/orders/15/assign-employee
Authorization: Bearer {token}
Content-Type: application/json

{
  "employeeId": 3
}
```

**Response:**
```json
{
  "message": "Employee assigned successfully",
  "orderId": 15,
  "employeeId": 3,
  "status": "ASSIGNED"
}
```

**📸 [View Postman Test →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

## 🛠️ Tech Stack

### Core Technologies
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Data JPA** - Database abstraction layer
- **Spring Security** - Authentication & authorization
- **MySQL 8.0** - Relational database
- **Maven** - Build automation tool

### Dependencies & Libraries
- **Lombok** - Reduce boilerplate code
- **JJWT (0.11.5)** - JWT token generation and validation
- **MySQL Connector (8.0.33)** - Database driver
- **Spring Validation** - Input validation
- **Spring Boot Starter Test** - Testing framework
- **Spring Security Test** - Security testing utilities

---

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA recommended)
- Postman (for API testing)

### Database Setup
1. Install MySQL 8.0
2. Create a database:
   ```sql
   CREATE DATABASE laundry_db;
   ```
3. Update credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/laundry_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

### Running the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend.git
   cd laundry-pickup-delivery-management-system-backend
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080`
   - API endpoints: `http://localhost:8080/api/`

---

## 🔐 Test Credentials

The application automatically seeds default users on first startup:

| Role | Email | Password | Access Level |
|------|-------|----------|--------------|
| **Admin** | `@admin.com` | `password123` | Full system access |
| **Customer** | `@gmail.com` | `password123` | Order placement & tracking |
| **Employee** | `@staff.com` | `password123` | Order processing |
| **Rider** | `@rider.com` | `password123` | Pickup & delivery |

---

## 📚 API Documentation

### 🔑 Authentication Endpoints

#### 1. User Registration
```http
POST /api/register
Content-Type: application/json

{
  "username": "johndoe",
  "password": "password123",
  "email": "john@example.com",
  "fullName": "John Doe",
  "phone": "0771234567",
  "address": "123 Main Street, Colombo"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 5,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "CUSTOMER"
}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. User Login
```http
POST /api/login
Content-Type: application/json

{
  "email": "@admin.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "admin",
  "email": "@admin.com",
  "role": "ADMIN"
}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

### 👤 Customer Endpoints

> **Authorization Required:** Bearer token with `CUSTOMER` role

#### 1. Get Customer Orders
```http
GET /api/customer/orders
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. Create New Order
```http
POST /api/customer/orders
Authorization: Bearer {token}
Content-Type: application/json

{
  "items": [
    {
      "name": "Shirt",
      "service": "Wash & Fold",
      "quantity": 5,
      "price": 150.00
    }
  ],
  "totalAmount": 750.00,
  "serviceType": "Wash & Fold",
  "address": "123 Customer Street"
}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 3. Get Customer Dashboard Stats
```http
GET /api/customer/dashboard/stats
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

### 👷 Employee Endpoints (⭐ UPDATED)

> **Authorization Required:** Bearer token with `EMPLOYEE` role

#### 1. Get Assigned Orders
```http
GET /api/employee/orders
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. Get Employee Order Status (⭐ NEW)
```http
GET /api/employee/orders/status
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 3. Update Order Status
```http
PUT /api/employee/orders/{id}/status
Authorization: Bearer {token}
Content-Type: application/json

{
  "status": "PROCESSING"
}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

### 🚴 Rider Endpoints

> **Authorization Required:** Bearer token with `RIDER` role

#### 1. Get Assigned Orders
```http
GET /api/rider/orders
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. Confirm Pickup
```http
POST /api/rider/orders/{id}/confirm-pickup
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 3. Get Rider Dashboard Stats
```http
GET /api/rider/dashboard/stats
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

### 👨‍💼 Admin Endpoints (⭐ UPDATED)

> **Authorization Required:** Bearer token with `ADMIN` role

#### 1. Get All Orders
```http
GET /api/admin/orders
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 2. Get All Employees (⭐ NEW)
```http
GET /api/admin/employees
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 3. Assign Employee to Order (⭐ UPDATED)
```http
PUT /api/admin/orders/{orderId}/assign-employee
Authorization: Bearer {token}
Content-Type: application/json

{
  "employeeId": 3
}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

#### 4. Get System Analytics
```http
GET /api/admin/analytics/stats
Authorization: Bearer {token}
```

**📸 [View Postman Screenshot →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

---

## 📸 Postman Testing Suite

### 🎯 Complete API Test Coverage

All **25 API endpoints** have been thoroughly tested with Postman. View the complete test suite with request/response examples:

<div align="center">

### **📁 [Access Complete Postman Screenshot Gallery →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

[![Postman Badge](https://img.shields.io/badge/Postman-100%25_Tested-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)

</div>

### 📊 Test Coverage Summary

| Module | Endpoints | Status | Screenshots |
|--------|-----------|--------|-------------|
| **Authentication** | 2 | ✅ Complete | [View →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot) |
| **Customer** | 4 | ✅ Complete | [View →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot) |
| **Employee** ⭐ | 5 | ✅ Complete | [View →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot) |
| **Rider** | 4 | ✅ Complete | [View →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot) |
| **Admin** ⭐ | 10 | ✅ Complete | [View →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot) |
| **Total** | **25** | **✅ 100%** | **[View All →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)** |

### 🚀 Quick Start with Postman

1. **Import Collection**
   - Navigate to: [Laundrymart Postman screeshot](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)
   - Download Postman collection (if available)
   - Or reference the screenshots for API testing

2. **Set Environment Variables**
   ```
   base_url: http://localhost:8080
   token: <copy from login response>
   admin_token: <admin user token>
   customer_token: <customer user token>
   employee_token: <employee user token>
   rider_token: <rider user token>
   ```

3. **Authentication Flow**
   ```
   Step 1: POST /api/login → Get token
   Step 2: Copy token from response
   Step 3: Use as Bearer token in Authorization header
   ```

### ✅ Testing Checklist

- ✅ User registration and login
- ✅ JWT token generation and validation
- ✅ Customer order creation and retrieval
- ✅ Admin order assignment (Employee & Rider)
- ✅ Employee order processing
- ✅ ⭐ NEW: Employee order status tracking
- ✅ ⭐ NEW: Employee management endpoints
- ✅ Rider pickup and delivery
- ✅ Dashboard statistics (All roles)
- ✅ Profile updates
- ✅ Error handling (400, 401, 403, 404, 500)
- ✅ Role-based access control

---

## 🏗️ Architecture

### Project Structure

```
src/main/java/com/laundry/
├── LaundryApplication.java          # Main application entry point
├── config/                          # Configuration classes
│   ├── SecurityConfig.java          # Spring Security configuration
│   ├── CorsConfig.java              # CORS configuration
│   ├── JwtConfig.java               # JWT configuration
│   └── DataSeeder.java              # Default user seeding
├── controller/                      # REST API controllers
│   ├── AuthController.java          # Authentication endpoints
│   ├── CustomerController.java      # Customer endpoints
│   ├── EmployeeController.java      # ⭐ Employee endpoints (UPDATED)
│   ├── RiderController.java         # Rider endpoints
│   └── AdminController.java         # ⭐ Admin endpoints (UPDATED)
├── dto/                             # Data Transfer Objects
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── OrderRequest.java
│   ├── EmployeeAssignmentRequest.java  # ⭐ NEW
│   └── ...
├── entity/                          # JPA entities
│   ├── User.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── ...
├── enums/                           # Enum classes
│   ├── OrderStatus.java
│   ├── UserRole.java
│   └── ServiceType.java
├── exception/                       # Exception handling
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── ...
├── repository/                      # JPA repositories
│   ├── UserRepository.java
│   ├── OrderRepository.java
│   └── OrderItemRepository.java
├── security/                        # Security components
│   ├── JwtTokenProvider.java
│   ├── JwtAuthenticationFilter.java
│   └── UserDetailsServiceImpl.java
└── service/                         # Business logic
    ├── AuthService.java
    ├── UserService.java
    ├── OrderService.java
    ├── CustomerService.java
    ├── EmployeeService.java         # ⭐ UPDATED
    ├── RiderService.java
    └── AdminService.java            # ⭐ UPDATED
```

### Design Patterns Used
- **MVC Pattern** - Model-View-Controller architecture
- **Repository Pattern** - Data access abstraction
- **DTO Pattern** - Data transfer objects for API communication
- **Dependency Injection** - Spring's IoC container
- **Service Layer Pattern** - Business logic separation
- **Factory Pattern** - Object creation
- **Singleton Pattern** - Spring bean management

---

## 📊 Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    role ENUM('ADMIN', 'CUSTOMER', 'EMPLOYEE', 'RIDER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Orders Table
```sql
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    employee_id BIGINT,
    rider_id BIGINT,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('PLACED', 'ASSIGNED', 'AT_LAUNDRY', 'PROCESSING', 
                'READY', 'DELIVERED', 'COMPLETED', 'CANCELLED') NOT NULL,
    service_type VARCHAR(50),
    item_count INT,
    address VARCHAR(255),
    progress_note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP,
    pickup_date TIMESTAMP,
    delivery_date TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES users(id),
    FOREIGN KEY (employee_id) REFERENCES users(id),
    FOREIGN KEY (rider_id) REFERENCES users(id)
);
```

### Order_Items Table
```sql
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    service VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);
```

---

## 🔒 Security

### JWT Authentication Flow
1. User sends credentials to `/api/login`
2. Server validates credentials
3. Server generates JWT token with user details and role
4. Client stores token (localStorage/sessionStorage)
5. Client includes token in `Authorization` header for protected routes
6. Server validates token on each request
7. Token expires after 24 hours

### Role-Based Access Control

| Endpoint Pattern | Admin | Customer | Employee | Rider |
|------------------|-------|----------|----------|-------|
| `/api/register` | ✅ | ✅ | ✅ | ✅ |
| `/api/login` | ✅ | ✅ | ✅ | ✅ |
| `/api/customer/**` | ❌ | ✅ | ❌ | ❌ |
| `/api/employee/**` | ❌ | ❌ | ✅ | ❌ |
| `/api/rider/**` | ❌ | ❌ | ❌ | ✅ |
| `/api/admin/**` | ✅ | ❌ | ❌ | ❌ |

---

## 🎯 Business Logic

### Order Processing Workflow

```
Customer Places Order (PLACED)
         ↓
Admin Assigns Employee & Rider (ASSIGNED)
         ↓
Rider Picks Up from Customer (AT_LAUNDRY)
         ↓
Employee Processes Laundry (PROCESSING)
         ↓
Processing Completed (READY)
         ↓
Rider Delivers to Customer (DELIVERED)
         ↓
Order Finalized (COMPLETED)
```

### Commission Calculation
- Riders earn **5% commission** on completed orders
- Formula: `commission = orderTotal * 0.05`
- Company retains **95%** of order value
- Commissions calculated on `COMPLETED` status only

---

## 🚨 Error Handling

### Error Response Format
```json
{
  "timestamp": "2026-02-14T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Order not found with id: 123",
  "path": "/api/customer/orders/123"
}
```

### Common HTTP Status Codes
- `200 OK` - Successful request
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid input data
- `401 Unauthorized` - Missing or invalid token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate resource
- `500 Internal Server Error` - Server error

---

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/laundry_db
spring.datasource.username=root
spring.datasource.password=root

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=mySecretKeyForJWTTokenGenerationAndValidation12345678901234567890
jwt.expiration=86400000

# Logging
logging.level.com.laundry=DEBUG
```

---

## 📊 Project Insights & Contributors

### 📈 Repository Statistics

<div align="center">

![Repository Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)
![Language](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk)
![Framework](https://img.shields.io/badge/Framework-Spring%20Boot-6DB33F?style=for-the-badge&logo=spring)
![Database](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql)

| 📦 Metric | 📊 Value |
|-----------|----------|
| **Total Commits** | 35+ |
| **Active Contributors** | 3 (Core Team) |
| **Primary Language** | Java (100%) |
| **Framework** | Spring Boot 3.2.0 |
| **Last Updated** | Feb 14, 2026 - 5:45 PM |
| **Project Duration** | 2 Months |
| **Repository Size** | 51.1 MB |
| **API Endpoints** | 25 (100% Tested) |
| **Test Coverage** | 100% with Postman |

</div>

---

### 👥 Contributors Overview

<div align="center">

<table>
  <tr>
    <th></th>
    <th>Contributor</th>
    <th>Role</th>
    <th>Backend Commits</th>
    <th>Frontend Commits</th>
    <th>Total Contributions</th>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/KawyaDissanayaka">
        <img src="https://avatars.githubusercontent.com/u/223992388?v=4" width="80" height="80" style="border-radius: 50%; border: 3px solid #4CAF50;" alt="KawyaDissanayaka"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/KawyaDissanayaka">
        <b>Kawya Dissanayaka</b><br/>
        <sub>@KawyaDissanayaka</sub>
      </a>
    </td>
    <td>🎯 Lead Developer<br/>📦 Project Owner</td>
    <td align="center"><b>24+</b></td>
    <td align="center"><b>24+</b></td>
    <td align="center"><b>🏆 48+</b></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/minidu1">
        <img src="https://avatars.githubusercontent.com/u/204583431?v=4" width="80" height="80" style="border-radius: 50%; border: 3px solid #FF9800;" alt="minidu1"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/minidu1">
        <b>Minidu Silva</b><br/>
        <sub>@minidu1</sub>
      </a>
    </td>
    <td>⚙️ Core Developer<br/>📊 Feature Specialist</td>
    <td align="center"><b>6+</b></td>
    <td align="center">-</td>
    <td align="center"><b>🥈 6+</b></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/DarshanaChinthaka">
        <img src="https://avatars.githubusercontent.com/u/214707803?v=4" width="80" height="80" style="border-radius: 50%; border: 3px solid #2196F3;" alt="DarshanaChinthaka"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/DarshanaChinthaka">
        <b>Darshana Chinthaka</b><br/>
        <sub>@DarshanaChinthaka</sub>
      </a>
    </td>
    <td>🗄️ Database Architect<br/>🧪 Testing Lead</td>
    <td align="center"><b>6+</b></td>
    <td align="center">-</td>
    <td align="center"><b>🥉 6+</b></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/shanilka1">
        <img src="https://avatars.githubusercontent.com/u/214608833?v=4" width="80" height="80" style="border-radius: 50%; border: 3px solid #9C27B0;" alt="shanilka1"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/shanilka1">
        <b>Shanilka Lakshan</b><br/>
        <sub>@shanilka1</sub>
      </a>
    </td>
    <td>🎨 Frontend Developer<br/>💻 UI Implementation</td>
    <td align="center">-</td>
    <td align="center"><b>4+</b></td>
    <td align="center"><b>4+</b></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/nimashagayathri">
        <img src="https://github.com/nimashagayathri.png" width="80" height="80" style="border-radius: 50%; border: 3px solid #E91E63;" alt="nimashagayathri"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/nimashagayathri">
        <b>Nimasha Gayathri</b><br/>
        <sub>@nimashagayathri</sub>
      </a>
    </td>
    <td>🎨 UI/UX Designer<br/>🖌️ Design Contributor</td>
    <td align="center">-</td>
    <td align="center">-</td>
    <td align="center">🎨 Design</td>
  </tr>
</table>

</div>


### 🏆 Top Contributors Hall of Fame

<div align="center">

#### 🥇 Gold Medal - Lead Developer & Project Architect

<a href="https://github.com/KawyaDissanayaka">
  <img src="https://avatars.githubusercontent.com/u/223992388?v=4" width="150" height="150" style="border-radius: 50%; border: 6px solid gold;"/>
</a>

### Kawya Dissanayaka

![Total Contributions](https://img.shields.io/badge/Total%20Contributions-48+-gold?style=for-the-badge&logo=github)
![Backend Commits](https://img.shields.io/badge/Backend%20Commits-24+-blue?style=for-the-badge&logo=java)
![Frontend Commits](https://img.shields.io/badge/Frontend%20Commits-24+-green?style=for-the-badge&logo=react)
![Documentation](https://img.shields.io/badge/Documentation-Complete-orange?style=for-the-badge&logo=markdown)

**Key Contributions:**
- 🔐 Complete Authentication & Authorization System
- 📦 Order Management Architecture
- 📚 Comprehensive API Documentation
- 🎨 System Design & Architecture
- ⚡ Performance Optimization
- 🛡️ Security Implementation
- ⭐ Employee Controller Development

**Impact Score:** ⭐⭐⭐⭐⭐ (5.0/5.0)

---

#### 🥈 Silver Medal - Core Developer & Feature Specialist

<a href="https://github.com/minidu1">
  <img src="https://avatars.githubusercontent.com/u/204583431?v=4" width="140" height="140" style="border-radius: 50%; border: 5px solid silver;"/>
</a>

### Minidu Silva

![Backend Commits](https://img.shields.io/badge/Backend%20Commits-6+-blue?style=for-the-badge&logo=spring)
![Features](https://img.shields.io/badge/New%20Features-3+-green?style=for-the-badge&logo=github)
![Latest Work](https://img.shields.io/badge/Latest-Today-orange?style=for-the-badge&logo=calendar)

**Key Contributions:**
- ⭐ Employee Order Status API (NEW)
- ⭐ Employee Assignment System (NEW)
- ⭐ Employee Management Endpoints (NEW)
- 👥 Employee Directory Implementation
- 📊 Admin Employee Dashboard

**Impact Score:** ⭐⭐⭐⭐⭐ (5.0/5.0)

---

#### 🥉 Bronze Medal - Database Architect & Testing Lead

<a href="https://github.com/DarshanaChinthaka">
  <img src="https://avatars.githubusercontent.com/u/214707803?v=4" width="130" height="130" style="border-radius: 50%; border: 4px solid #CD7F32;"/>
</a>

### Darshana Chinthaka

![Backend Commits](https://img.shields.io/badge/Backend%20Commits-6+-blue?style=for-the-badge&logo=spring)
![Database](https://img.shields.io/badge/Database-Specialist-green?style=for-the-badge&logo=mysql)
![Testing](https://img.shields.io/badge/Postman%20Testing-Expert-orange?style=for-the-badge&logo=postman)

**Key Contributions:**
- 🗄️ Database Schema Design & Optimization
- 👥 User Management System
- 🔄 Role Management Implementation
- 🧪 API Testing & Validation
- 📊 Order Management Features

**Impact Score:** ⭐⭐⭐⭐ (4.5/5.0)

---

### 🎨 Special Recognition - UI/UX Excellence

<a href="https://github.com/nimashagayathri">
  <img src="https://github.com/nimashagayathri.png" width="120" height="120" style="border-radius: 50%; border: 4px solid #9C27B0;"/>
</a>

### Nimasha Gayathri

![UI Design](https://img.shields.io/badge/UI%20Design-Complete-purple?style=for-the-badge&logo=figma)
![UX](https://img.shields.io/badge/UX-Optimization-pink?style=for-the-badge&logo=adobe)

**Key Contributions:**
- 🖌️ Complete UI/UX Design
- 🎨 Design System Creation
- 📐 Wireframing & Prototyping
- 💡 User-Centered Design Approach

**Impact Score:** ⭐⭐⭐⭐ (4.0/5.0)

</div>

---


### 🔗 Quick Links

<div align="center">

[![View Insights](https://img.shields.io/badge/📊-View%20Insights-blue?style=for-the-badge&logo=github)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/graphs/contributors)
[![Commit History](https://img.shields.io/badge/📜-Commit%20History-green?style=for-the-badge&logo=git)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)
[![Postman Tests](https://img.shields.io/badge/📸-Postman%20Tests-FF6C37?style=for-the-badge&logo=postman)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)
[![Frontend Repo](https://img.shields.io/badge/🎨-Frontend%20Repo-blue?style=for-the-badge&logo=react)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-frontend)

</div>

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

**Kawya Dissanayaka**
- GitHub: [@KawyaDissanayaka](https://github.com/KawyaDissanayaka)
- Repository: [Backend](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend) | [Frontend](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-frontend)

---

## 🔗 Related Repositories

- **Frontend Application**: [laundry-pickup-delivery-management-system-frontend](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-frontend)
- **Postman Test Suite**: [Laundrymart Postman screeshot](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)

---

<div align="center">

### 🙏 Thank You to All Contributors!

![Made with Love](https://img.shields.io/badge/Made%20with-❤️-red?style=for-the-badge)
![Team Work](https://img.shields.io/badge/Team-Work-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

**Last Updated:** February 14, 2026 - 5:45 PM IST

**Latest Release:** Employee Management System v1.0 🎉

**📸 [View Complete Postman Test Suite →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)**

</div>

---

## 📝 Notes

> **Postman Test Suite:** Complete API testing documentation with screenshots is available in the **[Laundrymart Postman screeshot](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/tree/main/Laundrymart%20Postman%20screeshot)** folder.

> **Commit counts are based on the most recent 30 commits.** For complete contribution history, please visit the [Insights page](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/graphs/contributors).

> **Environment Variables:** Remember to update sensitive data (database credentials, JWT secret) in production environments.

> **View More Commits:** [See all commits →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

[![GitHub stars](https://img.shields.io/github/stars/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/network/members)
[![GitHub watchers](https://img.shields.io/github/watchers/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/watchers)

</div>
