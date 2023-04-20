# Build stage
FROM openjdk:17-jdk-slim-buster As build
EXPOSE 8083
RUN apt-get update -y
RUN apt-get install -y curl binutils postgresql-client
WORKDIR /app
COPY . .
RUN ./gradlew clean
RUN ./gradlew build

# Runtime stage
FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/transactions.jar /app
CMD ["java", "-jar", "transactions.jar"]
