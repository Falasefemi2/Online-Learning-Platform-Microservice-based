# Online Learning Platform Microservice

This project is a microservices-based online learning platform. It consists of two main services: a `user-service` for handling user authentication and management, and a `course-server` which acts as a Eureka server for service discovery.

## Services

### User Service

*   **Port:** 8081
*   **Description:** Manages user registration, login, and authentication. It uses JWT for secure communication.
*   **Database:** MySQL
*   **API Endpoints:**
    *   `POST /api/v1/auth/register`: Register a new user.
    *   `POST /api/v1/auth/login`: Authenticate a user and receive a JWT token.

### Course Server (Eureka)

*   **Port:** 8761
*   **Description:** A Spring Cloud Netflix Eureka server that provides service discovery for the microservices in the platform.

## Getting Started

### Prerequisites

*   Java 24
*   Maven
*   MySQL

### Running the Application

1.  **Start the Course Server (Eureka):**
    *   Navigate to the `course-server` directory.
    *   Run the application using `mvn spring-boot:run`.

2.  **Start the User Service:**
    *   Navigate to the `user-service` directory.
    *   Make sure your MySQL server is running and the database `userdb` is created.
    *   Update the `application.yml` file with your MySQL username and password.
    *   Run the application using `mvn spring-boot:run`.

## API Usage

You can use the included `http-request/auth.http` file to test the authentication endpoints using an appropriate REST client.
