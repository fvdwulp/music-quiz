# Use an official JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the repo into the container
COPY . .

# Expose port (Render sets $PORT)
ENV PORT=8080
EXPOSE $PORT

# Run the JAR
CMD ["sh", "-c", "java -jar deploy/myserver-0.0.1-SNAPSHOT.jar"]
