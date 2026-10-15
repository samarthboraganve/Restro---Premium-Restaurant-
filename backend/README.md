# Restro Spring Boot Backend

A complete, production-ready RESTful API backend built with **Java 21/25**, **Spring Boot 3.3.5**, **Spring Data JPA (Hibernate)**, and **MySQL 8.0** for the **Restro** restaurant web application.

---

## 🛠 Features & Capabilities

- **🍽 Dishes Management (`/api/dishes`)**:
  - Full CRUD for restaurant dishes.
  - Category filtering (`?category=Pasta`) and featured dish filtering (`?featuredOnly=true`).
- **📅 Table Reservations (`/api/reservations`)**:
  - Direct booking creation with bean validation (name, email, phone, date, time, guest count).
  - Reservation lookup by date or customer email.
  - Status management (`PENDING`, `CONFIRMED`, `CANCELLED`, `COMPLETED`).
- **⭐ Customer Testimonials (`/api/testimonials`)**:
  - Retrieve approved customer reviews with star ratings (1-5).
  - Submit new reviews.
- **🕒 Operating Hours (`/api/timings`)**:
  - Weekly schedule and business opening hours.
- **❓ FAQs (`/api/faqs`)**:
  - Frequently asked questions and answers.
- **📬 Customer Inquiries (`/api/contact`)**:
  - Store customer inquiries and contact messages.
- **🌱 Automated Data Seeding**:
  - Automatically seeds dishes, timings, testimonials, and FAQs on the very first run if the database tables are empty.
- **🌐 CORS Enabled**:
  - Ready for Vite (`http://localhost:5173`) and Next.js / Create React App (`http://localhost:3000`).

---

## 🚀 How to Run

### Prerequisites
1. **Java JDK 21+** (or Java 25)
2. **Maven 3.9+** (or use included `mvnw.cmd` wrapper)
3. **MySQL Server 8.0+** running locally on port `3306`

### 1. Database Setup
The application connects to MySQL with:
- **Host**: `localhost:3306`
- **Database**: `restro_db`
- **User**: `root`
- **Password**: `Samarth@30` *(Configurable in `src/main/resources/application.properties`)*

The database and tables are created automatically on startup by Hibernate. You can also manually inspect or run the SQL script:
```powershell
mysql -u root -p < restro_db.sql
```

### 2. Start the Backend
From `C:\Users\Admin\Desktop\Restro\backend`:
```powershell
# Using installed Maven
mvn spring-boot:run

# OR using the pre-built JAR
java -jar target\restro-backend-1.0.0.jar
```
The server will start at: **`http://localhost:8081`**

---

## 📡 REST API Reference

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/dishes` | Get all available dishes |
| `GET` | `/api/dishes?category=Pasta` | Filter dishes by category |
| `GET` | `/api/dishes?featuredOnly=true` | Get featured dishes |
| `GET` | `/api/dishes/{id}` | Get dish by ID |
| `POST` | `/api/dishes` | Add a new dish |
| `PUT` | `/api/dishes/{id}` | Update an existing dish |
| `DELETE` | `/api/dishes/{id}` | Delete dish |
| `POST` | `/api/reservations` | Create a table reservation |
| `GET` | `/api/reservations` | List reservations (supports `?date=YYYY-MM-DD` and `?email=...`) |
| `GET` | `/api/reservations/{id}` | Get reservation details |
| `PATCH` | `/api/reservations/{id}/status?status=CONFIRMED` | Update reservation status |
| `DELETE` | `/api/reservations/{id}` | Cancel reservation |
| `GET` | `/api/timings` | Get restaurant operating hours |
| `GET` | `/api/testimonials` | Get approved reviews |
| `POST` | `/api/testimonials` | Submit a customer review |
| `GET` | `/api/faqs` | Get FAQs |
| `POST` | `/api/contact` | Submit contact inquiry |

---

## 🧪 Testing with PowerShell / cURL

### 1. Get All Dishes
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/dishes" | ConvertTo-Json -Depth 3
```

### 2. Book a Table
```powershell
$body = @{
    customerName = "Sofia Martinez"
    customerEmail = "sofia@example.com"
    customerPhone = "+1 555-0144"
    reservationDate = "2026-10-25"
    reservationTime = "07:30 PM"
    guestCount = 4
    specialRequests = "Window booth seat please"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8081/api/reservations" -Method Post -ContentType "application/json" -Body $body | ConvertTo-Json -Depth 3
```
