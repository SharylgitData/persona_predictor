# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Gradle wrapper and related files
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

# Copy the rest of the app
COPY src/ src/
COPY build/libs/persona_predictor-0.0.1-SNAPSHOT.jar app.jar

# Set the port Render expects (via $PORT)
ENV PORT=8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
