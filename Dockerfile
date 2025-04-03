# Stage 1: Build the JAR using Gradle
FROM gradle:7.6.1-jdk17 AS builder

WORKDIR /app

# Copy only necessary files for dependency caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Fetch dependencies (caches this layer)
RUN ./gradlew build || return 0

# Now copy the full source code
COPY . .

# Build the application
RUN ./gradlew clean build

# Stage 2: Run the app using a slim Java image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the generated fat JAR from builder stage
COPY --from=builder /app/build/libs/persona_predictor-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
