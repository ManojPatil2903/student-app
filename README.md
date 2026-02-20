# Student Management App

Spring Boot REST API with MySQL database connectivity to manage student records.

---

## Project Structure

```
student-app/
├── src/main/java/com/example/studentapp/
│   ├── StudentAppApplication.java   ← Entry point
│   ├── model/
│   │   └── Student.java             ← Entity (DB table)
│   ├── repository/
│   │   └── StudentRepository.java   ← DB queries
│   ├── service/
│   │   └── StudentService.java      ← Business logic
│   └── controller/
│       └── StudentController.java   ← REST endpoints
├── src/main/resources/
│   └── application.properties       ← DB config
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

---

## Run Locally

### Option 1 — With Docker Compose (Recommended)
```bash
docker-compose up --build
```
App starts at: http://localhost:8080

### Option 2 — Without Docker
1. Start MySQL and create database `studentdb`
2. Update `application.properties` with your credentials
3. Run: `mvn spring-boot:run`

---

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/students` | Create new student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/roll/{rollNo}` | Get by roll number |
| GET | `/api/students/search?name=John` | Search by name |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

---

## Sample Request Body (POST/PUT)

```json
{
  "rollNo": "CS101",
  "name": "John Doe",
  "address": "123 Main Street, Mumbai",
  "phoneNumber": "9876543210",
  "email": "john.doe@email.com"
}
```

## Sample Response

```json
{
  "id": 1,
  "rollNo": "CS101",
  "name": "John Doe",
  "address": "123 Main Street, Mumbai",
  "phoneNumber": "9876543210",
  "email": "john.doe@email.com"
}
```

---

## Database Table (auto-created by Hibernate)

```sql
CREATE TABLE students (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  roll_no      VARCHAR(255) UNIQUE NOT NULL,
  name         VARCHAR(100) NOT NULL,
  address      TEXT NOT NULL,
  phone_number VARCHAR(10) UNIQUE NOT NULL,
  email        VARCHAR(255)
);
```
