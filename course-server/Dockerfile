# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

RUN mvn clean install -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy built JAR to runtime container
COPY --from=build /app/target/course-server-0.0.1-SNAPSHOT.jar .

# Expose application port
EXPOSE 8761

# Run the application
CMD ["java", "-jar", "course-server-0.0.1-SNAPSHOT.jar"]
