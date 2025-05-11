# Exemple pour un projet Java avec Maven
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target /app/target
CMD ["java", "-jar", "/app/target/votre-app.jar"]