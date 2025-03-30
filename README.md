# E-Commerce Order Management System

## 📌 Overview
This is a Spring Boot-based **E-Commerce Order Management System** that allows users to browse products, place orders and manage order statuses. The system also supports caching for secure role-based access control.

## 🚀 Features
- **User Authentication & Authorization** (Admin, Customer, Super Admin roles)
- **Product Management** 
- **Order Placement & Processing**
- **Role-Based Access Control (RBAC)**

## 🏗️ Tech Stack
- **Backend**: Java 17, Spring Boot 3, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Authentication**: Spring Security with BCrypt Password Hashing
- **Build Tool**: Gradle

---
## 🛠️ Installation & Setup

### 1️⃣ **Clone the Repository**
```sh
 git https://github.com/yogeshgaikwad2016/spring-boot_EComm-Order-management.git
 cd <project-directory>
```

### 2️⃣ **Configure Database** (PostgreSQL/MySQL)
Update `application.yml`:
```properties
  datasource:
    url: jdbc:mysql://localhost:3306/ecomm_om
    username: your-username
    password: your-pass
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 3️⃣ **Build & Run Application**
```sh
./gradlew clean install
./gradlew bootRun
```

### 4️⃣ **Access API**
- Swagger UI: `http://localhost:8086/swagger-ui.html`
- API Base URL: `http://localhost:8086/api/v1`

---
## 🔐 Authentication & Authorization
The system implements **Spring Security** with different access levels:

| Role          | Permissions |
|--------------|------------|
| **ADMIN**    | Can manage users, orders, and products |
| **CUSTOMER** | Can browse products, place orders, and request refunds |
| **SUPER**    | Can delete any order and perform system-wide operations |

---
## 🔄 API Endpoints

### **User Management**
| Method | Endpoint               | Access |
|--------|------------------------|--------|
| `POST` | `/api/v1/user`         | Public (Register) |
| `GET`  | `/api/v1/users`        | Admin Only |

### **Product Management**
| Method | Endpoint                  | Access |
|--------|---------------------------|--------|
| `GET`  | `/api/v1/product/*`       | Public |
| `POST` | `/api/v1/product`         | Admin Only |
| `PUT`  | `/api/v1/product/{id}`    | Admin Only |
| `DELETE` | `/api/v1/product/{id}`  | Super Only |

### **Order Management**
| Method | Endpoint                     | Access |
|--------|------------------------------|--------|
| `POST` | `/api/v1/order`              | Customer Only |
| `PUT`  | `/api/v1/order/status/{id}`  | Admin Only |
| `DELETE` | `/api/v1/order/{id}`       | Super Only |

---
## 🤝 Contributing
Feel free to fork the repo and submit pull requests.

---
## 📧 Contact
For support or feature requests, reach out via email: `gyogesh2097@gmail.com`.

