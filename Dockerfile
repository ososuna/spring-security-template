# -------- Stage 1: Build the application --------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies (to leverage caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Build the Spring Boot JAR (skip tests for faster build, remove -DskipTests if you want tests to run)
RUN mvn clean package -DskipTests

# -------- Stage 2: Run the application --------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the built jar from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the application directly
ENTRYPOINT ["java", "-jar", "/app/app.jar"]