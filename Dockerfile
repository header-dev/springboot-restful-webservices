FROM openjdk:21-jdk-slim
COPY . /app
WORKDIR /app
RUN ./mvnw package
CMD ["java", "-jar", "target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar"]