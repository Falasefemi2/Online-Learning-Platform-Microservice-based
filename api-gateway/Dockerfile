# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy everything to the build container
# Copy pom and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the project and skip tests
RUN mvn clean install -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar .

# Expose port (for documentation purposes)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]
