# Stage 1: Compile
FROM eclipse-temurin:21 AS builder
WORKDIR /app

COPY src/ ./src/

RUN find src -name "*.java" > sources.txt && javac -d out @sources.txt

# Stage 2: Run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app



COPY --from=builder /app/out ./out

EXPOSE 8080
ENTRYPOINT ["java", "-cp", "out", "Main"]