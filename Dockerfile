# Fase build: compila con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase run: avvia il JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/BugBoard26-Server-1.0-SNAPSHOT-shaded.jar app.jar
CMD ["java","-jar","app.jar"]
