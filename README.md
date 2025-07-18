# Online Learning Platform Microservice

This project is a microservices-based online learning platform. It consists of five main services: `api-gateway`, `user-service`, `course-service`, `enrollment-service` and a `course-server` which acts as a Eureka server for service discovery.

## Services

### API Gateway

*   **Port:** 8080
*   **Description:** The single entry point for all client requests. It routes requests to the appropriate microservice and handles cross-cutting concerns like authentication and logging.

### User Service

*   **Port:** 8081
*   **Description:** Manages user registration, login, and authentication. It uses JWT for secure communication.
*   **Database:** MySQL
*   **API Endpoints:**
    *   `POST /api/v1/auth/register`: Register a new user.
    *   `POST /api/v1/auth/login`: Authenticate a user and receive a JWT token.

### Course Service

*   **Port:** 8082
*   **Description:** Manages course-related operations, including creating, retrieving, updating, and deleting courses.
*   **Database:** MySQL
*   **API Endpoints:**
    *   `POST /api/v1/courses`: Create a new course.
    *   `GET /api/v1/courses/{id}`: Retrieve a course by ID.
    *   `GET /api/v1/courses`: Retrieve all courses.
    *   `PUT /api/v1/courses/{id}`: Update an existing course.
    *   `DELETE /api/v1/courses/{id}`: Delete a course by ID.

### Enrollment Service

*   **Port:** 8083
*   **Description:** Manages student enrollment in courses.
*   **Database:** MySQL
*   **API Endpoints:**
    *   `POST /api/v1/enrollments`: Enroll a student in a course.
    *   `GET /api/v1/enrollments/student/{studentId}`: Retrieve all enrollments for a student.

### Course Server (Eureka)

*   **Port:** 8761
*   **Description:** A Spring Cloud Netflix Eureka server that provides service discovery for the microservices in the platform.

## Getting Started

### Prerequisites

*   Java 21
*   Maven
*   Docker
*   MySQL

### Running the Application with Docker

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Femi-P/online-learning-platform-microservice.git
    ```
2.  **Build the project:**
    ```bash
    mvn clean install -DskipTests
    ```
3.  **Run the application:**
    ```bash
    docker-compose up -d
    ```

### Running the Application without Docker

1.  **Start the Course Server (Eureka):**
    *   Navigate to the `course-server` directory.
    *   Run the application using `mvn spring-boot:run`.

2.  **Start the User Service:**
    *   Navigate to the `user-service` directory.
    *   Make sure your MySQL server is running and the database `userdb` is created.
    *   Update the `application.yml` file with your MySQL username and password.
    *   Run the application using `mvn spring-boot:run`.

3.  **Start the Course Service:**
    *   Navigate to the `course-service` directory.
    *   Make sure your MySQL server is running and the database `coursedb` is created.
    *   Update the `application.yml` file with your MySQL username and password.
    *   Run the application using `mvn spring-boot:run`.

4.  **Start the Enrollment Service:**
    *   Navigate to the `enrollment-service` directory.
    *   Make sure your MySQL server is running and the database `enrollment_db` is created.
    *   Update the `application.yml` file with your MySQL username and password.
    *   Run the application using `mvn spring-boot:run`.

5.  **Start the API Gateway:**
    *   Navigate to the `api-gateway` directory.
    *   Run the application using `mvn spring-boot:run`.

## API Usage

You can use the included `http-request` files to test the API endpoints using an appropriate REST client.

*   `http-request/auth.http`: User authentication endpoints.
*   `http-request/course.http`: Course management endpoints.
*   `http-request/enrollment.http`: Enrollment management endpoints.
*   `http-request/gateway.http`: API gateway endpoints.