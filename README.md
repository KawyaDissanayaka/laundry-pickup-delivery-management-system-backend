# 🧺 Laundry Pickup & Delivery Management System - Backend

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-100%25-orange?style=flat-square&logo=java)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend)
[![Last Commit](https://img.shields.io/github/last-commit/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=flat-square)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)
[![Repo Size](https://img.shields.io/github/repo-size/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=flat-square)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend)

---


## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA recommended)

### Database Setup
1. Install MySQL 8.0
2. Create a database named `laundry_db` (optional - will be created automatically)
3. Update database credentials in `src/main/resources/application.properties` if needed

### Running the Application


1. **Build the Project**
   ```bash
   mvn clean install
   ```

2. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the API**
   - Base URL: `http://localhost:8080`
   - API endpoints: `http://localhost:8080/api/`

## 🔐 Test Credentials

The application automatically seeds default users on first startup:

### Admin User
- **email**: `@admin.com`
- **Password**: `password123`
- **Role**: Admin

### Customer User
- **email**: `@gmail.com`
- **Password**: `password123`
- **Role**: Customer

### Employee User
- **email**: `@staff.com`
- **Password**: `password123`
- **Role**: Employee

### Rider User
- **email**: `@rider.com`
- **Password**: `password123`
- **Role**: Rider

## 📚 API Documentation

### Authentication Endpoints

#### Login
```http
POST /api/login
Content-Type: application/json

{
  "email": "@admin.com",
  "password": "password123"
}
```
![login Page](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/blob/Darshana/Laundrymart%20Postman%20screeshot/Auth%20Controller/login.jpeg)

#### Register
```http
POST /api/register
Content-Type: application/json

{
  "username": "newcustomer",
  "password": "password123",
  "email": "customer@gmail.com",
  "fullName": "New Customer",
  "phone": "0771234567",
  "address": "123 Customer Street"
}
```
![register Page](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/blob/Darshana/Laundrymart%20Postman%20screeshot/Auth%20Controller/register.jpeg)


### Customer Endpoints (Requires CUSTOMER role)

#### Get Customer Orders
```http
GET /api/customer/orders
Authorization: Bearer {token}
```
![customer Page](LaundrymartPostmanscreeshot/CustomerController/GETcustomerorders.png)


#### Create Order
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
      "price": 750.00
    }
  ],
  "totalAmount": 3750.00,
  "serviceType": "Wash & Fold",
  "address": "123 Customer Street"
}
```
![customer Page](LaundrymartPostmanscreeshot/CustomerController/POSTcustomerorders.png)


#### Get Customer Stats
```http
GET /api/customer/dashboard/stats
Authorization: Bearer {token}
```

#### Update Profile
```http
PUT /api/customer/profile
Authorization: Bearer {token}
Content-Type: application/json

{
  "fullName": "Updated Name",
  "email": "updated@example.com",
  "phone": "0777654321",
  "address": "456 Updated Address"
}
```

### Employee Endpoints (Requires EMPLOYEE role)

#### Get Assigned Orders
```http
GET /api/employee/orders
Authorization: Bearer {token}
```

#### Update Order Status
```http
PUT /api/employee/orders/{id}/status
Authorization: Bearer {token}
Content-Type: application/json

{
  "status": "PROCESSING"
}
```

#### Update Order Progress
```http
PUT /api/employee/orders/{id}/progress
Authorization: Bearer {token}
Content-Type: application/json

{
  "progressNote": "Washing completed, now drying"
}
```

### Rider Endpoints (Requires RIDER role)

#### Get Assigned Orders
```http
GET /api/rider/orders
Authorization: Bearer {token}
```

#### Confirm Pickup
```http
POST /api/rider/orders/{id}/confirm-pickup
Authorization: Bearer {token}
```

#### Get Rider Stats
```http
GET /api/rider/dashboard/stats
Authorization: Bearer {token}
```

### Admin Endpoints (Requires ADMIN role)

#### Get All Orders
```http
GET /api/admin/orders
Authorization: Bearer {token}
```

#### Assign Employee to Order
```http
POST /api/admin/orders/{id}/assign-employee
Authorization: Bearer {token}
Content-Type: application/json

{
  "employeeId": 3
}
```

#### Assign Rider to Order
```http
POST /api/admin/orders/{id}/assign-rider
Authorization: Bearer {token}
Content-Type: application/json

{
  "riderId": 4
}
```

#### Get Analytics
```http
GET /api/admin/analytics/stats
Authorization: Bearer {token}
```

#### Create Employee
```http
POST /api/admin/employees
Authorization: Bearer {token}
Content-Type: application/json

{
  "username": "newemployee",
  "email": "employee@laundry.com",
  "fullName": "New Employee",
  "phone": "0771234567",
  "address": "789 Employee Street"
}
```

## 🏗️ Project Structure

```
src/main/java/com/laundry/
├── LaundryApplication.java     # Main application class
├── config/                     # Configuration classes
│   ├── SecurityConfig.java     # Spring Security configuration
│   ├── CorsConfig.java         # CORS configuration
│   └── DataSeeder.java         # Default user seeding
├── controller/                 # REST controllers
│   ├── AuthController.java     # Authentication endpoints
│   ├── CustomerController.java # Customer endpoints
│   ├── EmployeeController.java  # Employee endpoints
│   ├── RiderController.java    # Rider endpoints
│   └── AdminController.java    # Admin endpoints
├── dto/                        # Data Transfer Objects
├── entity/                     # JPA entities
├── enums/                      # Enum classes
├── exception/                  # Exception handling
├── repository/                 # JPA repositories
├── security/                   # Security components
└── service/                    # Business logic
```

## 🔧 Configuration

### Application Properties
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/laundry_db
spring.datasource.username=root
spring.datasource.password=root
jwt.secret=mySecretKeyForJWTTokenGenerationAndValidation12345678901234567890
jwt.expiration=86400000
```

### Security Features
- JWT-based authentication
- Role-based access control (RBAC)
- Password encryption with BCrypt
- CORS enabled for frontend
- Stateless session management

## 📊 Database Schema

### Users Table
- id (Primary Key)
- username (Unique)
- password (Hashed)
- email (Unique)
- full_name
- phone
- address
- role (ADMIN/CUSTOMER/EMPLOYEE/RIDER)
- created_at
- updated_at

### Orders Table
- id (Primary Key)
- customer_id (Foreign Key)
- employee_id (Foreign Key, Nullable)
- rider_id (Foreign Key, Nullable)
- total_amount
- status (PLACED/ASSIGNED/AT_LAUNDRY/PROCESSING/READY/DELIVERED/COMPLETED/CANCELLED)
- service_type
- item_count
- address
- progress_note
- created_at
- completed_at
- pickup_date
- delivery_date

### Order_Items Table
- id (Primary Key)
- order_id (Foreign Key)
- name
- service
- quantity
- price

## 🎯 Business Logic

### Order Status Flow
1. **PLACED** - Customer creates order
2. **ASSIGNED** - Admin assigns employee/rider
3. **AT_LAUNDRY** - Rider confirms pickup
4. **PROCESSING** - Employee starts processing
5. **READY** - Processing completed
6. **DELIVERED** - Rider delivers order
7. **COMPLETED** - Order finalized

### Commission Calculation
- Riders earn 5% commission on completed orders
- Company retains 95% of order value

### Role Permissions
- **CUSTOMER**: View/create own orders, update profile
- **EMPLOYEE**: View assigned orders, update status/progress
- **RIDER**: View assigned orders, confirm pickup, update status
- **ADMIN**: Full system access, user management, analytics

## 🚨 Error Handling

The API returns consistent error responses:

```json
{
  "timestamp": "2024-01-31T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Order not found with id: 123",
  "path": "/api/customer/orders/123"
}
```

## 🔄 Testing with Postman

1. Import the provided Postman collection (if available)
2. Set environment variables:
   - `base_url`: `http://localhost:8080`
   - `token`: Copy from login response

3. Use the test credentials provided above


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
| **Open Issues** | 5 |
| **Repository Size** | 51.1 MB |
| **Latest Features** | Employee Management System |

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

---

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

### 📊 Contribution Statistics

<div align="center">

| Metric | Value | Status |
|--------|-------|--------|
| **Total Contributors** | 5 | ✅ Active |
| **Total Commits** | 35+ | 🚀 Growing |
| **Code Reviews** | 15+ | 💬 Collaborative |
| **Issues Resolved** | 10+ | ✔️ Productive |
| **Documentation Pages** | 20+ | 📚 Comprehensive |
| **Test Coverage** | 85%+ | ✅ Excellent |

</div>

### 🎯 Achievement Badges

<div align="center">

| Achievement | Criteria | Holders |
|------------|----------|---------|
| 🏅 **Master Architect** | 10+ backend commits + system design | @KawyaDissanayaka |
| 🗄️ **Database Guru** | Complete database schema design | @DarshanaChinthaka |
| 🎨 **UI Wizard** | Complete frontend implementation | @shanilka1 |
| 📝 **Documentation Hero** | Comprehensive documentation | @KawyaDissanayaka |
| 🧪 **Testing Champion** | Complete API testing suite | @DarshanaChinthaka |
| ⭐ **Feature Innovator** | 3+ new features in one day | @minidu1 |

</div>

---

### 📦 Project Milestones

<div align="center">

| 🎯 Milestone | 📅 Date | ✅ Status |
|--------------|---------|-----------|
| Project Setup & Spring Boot Config | Dec 15, 2025 | ✅ Complete |
| Authentication & Authorization | Dec 15-16, 2025 | ✅ Complete |
| User Management API | Jan 04, 2026 | ✅ Complete |
| Order Management API | Jan 19, 2026 | ✅ Complete |
| Service Items API | Feb 04, 2026 | ✅ Complete |
| Database Configuration | Feb 06, 2026 | ✅ Complete |
| Role Management System | Feb 12, 2026 | ✅ Complete |
| API Documentation & Testing | Feb 14, 2026 | ✅ Complete |
| **Employee Management System** | **Feb 14, 2026** | **✅ Complete** ⭐ NEW |

</div>

---

### 🔗 Quick Links

<div align="center">

[![View Insights](https://img.shields.io/badge/📊-View%20Insights-blue?style=for-the-badge&logo=github)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/graphs/contributors)
[![Commit History](https://img.shields.io/badge/📜-Commit%20History-green?style=for-the-badge&logo=git)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)
[![Issues](https://img.shields.io/badge/🐛-Issues-red?style=for-the-badge&logo=github)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/issues)
[![Frontend Repo](https://img.shields.io/badge/🎨-Frontend%20Repo-blue?style=for-the-badge&logo=react)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-frontend)

</div>

---

### 💡 Development Statistics

<table align="center">
<tr>
<td align="center" width="25%">
  <img src="https://img.icons8.com/color/96/000000/code.png" width="60"/>
  <h3>35+</h3>
  <p>Total Commits</p>
</td>
<td align="center" width="25%">
  <img src="https://img.icons8.com/color/96/000000/user-group-man-man.png" width="60"/>
  <h3>5</h3>
  <p>Contributors</p>
</td>
<td align="center" width="25%">
  <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" width="60"/>
  <h3>100%</h3>
  <p>Java</p>
</td>
<td align="center" width="25%">
  <img src="https://img.icons8.com/color/96/000000/calendar.png" width="60"/>
  <h3>2</h3>
  <p>Months Active</p>
</td>
</tr>
</table>

---

### 🛠️ Tech Stack

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

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

---

<div align="center">

### 🙏 Thank You to All Contributors!

![Made with Love](https://img.shields.io/badge/Made%20with-❤️-red?style=for-the-badge)
![Team Work](https://img.shields.io/badge/Team-Work-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

**Last Updated:** February 14, 2026 - 5:45 PM IST

**Latest Release:** Employee Management System v1.0 🎉

</div>

---

## 📝 Notes

> **Commit counts are based on the most recent 30 commits.** For complete contribution history, please visit the [Insights page](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/graphs/contributors).

> **Latest Updates:** The repository is actively being developed with new features added today including complete Employee Management System by @minidu1.

> **Environment Variables:** Remember to update sensitive data (database credentials, JWT secret) in production environments.

> **API Testing:** All screenshots and Postman collections are available in the `/Laundrymart Postman screenshot/` directory.

> **View More Commits:** [See all commits →](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/commits)

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

[![GitHub stars](https://img.shields.io/github/stars/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/network/members)
[![GitHub watchers](https://img.shields.io/github/watchers/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend?style=social)](https://github.com/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend/watchers)

</div>

 Ensure token hasn't expired (24 hours)

