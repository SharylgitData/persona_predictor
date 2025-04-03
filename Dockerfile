# Stage 1: Build the JAR using Gradle
FROM gradle:8.12.1-jdk21 AS builder


WORKDIR /app

# Copy everything (including build.gradle)
COPY . .

# Force rebuild with clean environment
RUN ./gradlew clean build --no-daemon

# Stage 2: Use lightweight JDK image to run app
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/persona_predictor-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
