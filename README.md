# Laundry Management System Backend

![GitHub last commit](https://img.shields.io/github/last-commit/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend
)
![GitHub repo size](https://img.shields.io/github/repo-size/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend
)
![GitHub issues](https://img.shields.io/github/issues/KawyaDissanayaka/laundry-pickup-delivery-management-system-backend
)


A complete Spring Boot backend for the Laundry Management System with JWT authentication, role-based access control, and comprehensive order management.

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

### Customer Endpoints (Requires CUSTOMER role)

#### Get Customer Orders
```http
GET /api/customer/orders
Authorization: Bearer {token}
```

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

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL is running
   - Check database credentials in application.properties
   - Verify database exists

2. **JWT Token Issues**
   - Check token is included in Authorization header
   - Format: `Bearer {token}`
   - Ensure token hasn't expired (24 hours)

3. **CORS Issues**
   - Verify frontend URL is in allowed origins
   - Check browser console for CORS errors

4. **Permission Denied**
   - Verify user has correct role
   - Check endpoint permissions

## 📝 Development Notes

- Uses Spring Boot 3.x with Java 17
- JWT tokens expire after 24 hours
- Passwords are hashed with BCrypt
- All timestamps are in UTC
- Database schema is auto-updated on startup
- Default users are created automatically on first run

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.
