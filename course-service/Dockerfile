# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy everything (source + pom)
# Copy pom and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build with Maven and skip tests
RUN mvn clean install -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/course-service-0.0.1-SNAPSHOT.jar .

# Expose the port your service uses (adjust if needed)
EXPOSE 8082

# Run the jar
CMD ["java", "-jar", "course-service-0.0.1-SNAPSHOT.jar"]
