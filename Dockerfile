# Use a Java runtime image
FROM amazoncorretto:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/Integration2-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
