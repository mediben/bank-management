# Build stage
FROM openjdk:17-jdk-slim-buster As build
EXPOSE 8081
RUN apt-get update -y
RUN apt-get install -y curl binutils postgresql-client
WORKDIR /app
COPY . .
RUN ./gradlew clean
RUN ./gradlew build

# Runtime stage
FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/customers.jar /app
CMD ["java", "-jar", "customers.jar"]
