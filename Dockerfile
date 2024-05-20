FROM amazoncorretto:17-al2023-headless

WORKDIR /app

COPY src/main/resources/application.yml /app/application.yml
COPY target/rest-service-0.0.1-SNAPSHOT.jar /app/rest-service-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/rest-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080