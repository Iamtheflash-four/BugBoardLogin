FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia il jar generato (es. target/loginserver.jar)
COPY target/loginserver.jar app.jar

# Espone la porta (Render la sostituirà con $PORT)
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
