# ── Stage 1: Build ────────────────────────────────────
FROM amazoncorretto:17-alpine AS builder

RUN apk add --no-cache maven

WORKDIR /app

COPY pom.xml .
# Download dependencies first (layer caching)
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# ── Stage 2: Run ──────────────────────────────────────
FROM amazoncorretto:17-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
