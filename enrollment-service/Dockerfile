# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/enrollment-service-0.0.1-SNAPSHOT.jar .

# Change this if your service runs on a different port (e.g., 8083)
EXPOSE 8083

CMD ["java", "-jar", "enrollment-service-0.0.1-SNAPSHOT.jar"]
