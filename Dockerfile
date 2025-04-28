# Erstes Stage: Baue dein Projekt mit Gradle und Java 21
FROM gradle:8.5.0-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Zweites Stage: Nur die fertige App mit Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENV PORT 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
