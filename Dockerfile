#  Stage 1: Projekt mit Gradle (Wrapper) und Java 21 bauen
FROM gradle:8.5.0-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon -x test

#  Stage 2: Nur das fertige JAR ausführen (Production)
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar"]

