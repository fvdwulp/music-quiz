# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /build

# Copy pom first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests


# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /build/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["sh", "-c", "java -jar app.jar"]
