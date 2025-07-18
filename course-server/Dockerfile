# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy built JAR to runtime container
COPY --from=build /app/target/course-server-0.0.1-SNAPSHOT.jar .

# Expose application port
EXPOSE 8082

# Run the application
CMD ["java", "-jar", "course-server-0.0.1-SNAPSHOT.jar"]
