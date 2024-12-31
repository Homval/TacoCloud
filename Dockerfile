FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY src/ src
COPY pom.xml ./
RUN mvn package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/TacoCloud-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "TacoCloud-0.0.1-SNAPSHOT.jar"]

