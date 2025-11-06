FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia esplicita del jar "shaded" prodotto dal build
COPY --from=build /workspace/target/*-shaded.jar app.jar

EXPOSE 8080

# Avvia usando -cp e indicando la Main class (service.Main)
# così non dipendiamo dal manifest del jar
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -cp app.jar service.Main"]
