# Fase build: compila con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
RUN ls -l /app/target   # debug: mostra i JAR generati

# Fase run: avvia il JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java","-jar","/app/app.jar"]


