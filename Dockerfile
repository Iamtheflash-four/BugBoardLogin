FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

RUN jar tf target/app.jar | grep --color=always jersey

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /workspace/target/app.jar .
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]


