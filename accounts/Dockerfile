# Build stage
FROM openjdk:17-jdk-slim-buster As build
EXPOSE 8082
RUN apt-get update -y
RUN apt-get install -y curl binutils postgresql-client && rm -rf /var/lib/apt/lists/* 
WORKDIR /app
COPY . .
RUN ./gradlew clean
RUN ./gradlew build -i
# --stacktrace

# Runtime stage
FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/accounts.jar /app
CMD ["java", "-jar", "accounts.jar"]
