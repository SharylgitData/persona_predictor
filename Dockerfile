
# Use official lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/persona_predictor-0.0.1-SNAPSHOT.jar app.jar

# Set environment variable for PORT (default to 8080 if not provided)
ENV PORT=8080

# Expose the port (Render scans this)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
