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

COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar .

# Update this to your actual port from application.yml
EXPOSE 8081

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
