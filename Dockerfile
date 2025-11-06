# Stage di build
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Stage runtime più leggero
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar

# Esporre una porta (opzionale, Render fornisce $PORT)
EXPOSE 8080

# Assicurarsi che l'app usi la porta fornita da Render ($PORT)
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]
