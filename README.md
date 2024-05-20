# Java-challenge
 Java8+ challenge. Spring, SpringBoot, Rest, Dependency Injection

- Java 8
- JUnit5
- Swagger
- MongoDB
- Docker
- Docker Compose

# Build

Use the integrated mvn wrapper tool
```shell
./mvnw install
```

Don't forget to add valid read/write permissions in case you get a `Permission Denied` error
```shell
sudo chmod +x mvnw
```

# Run Locally

You need to have mongoDB installed or spin up a Docker container from a mongo image, for a more compatible configuration refer to the Run in Docker section

Either use a Run Configuration in IntelliJ or do it from the command line
```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
It will start the application with the active profile dev

# Run in Docker

You need to have Docker and docker-compose installed, refer to the official installations guide in https://docs.docker.com/engine/install/

Build the images with docker compose
```shell
docker compose build
```

Start up the containers
```shell
docker compose up
```

Gracefully shutdown
```shell
docker compose down
```

In order to avoid caching issues when making a change and remove the volumes along the containers use the flag `-v`
```shell
docker compose down -v
```

If you only want to build and start up a mongo container simply do
```shell
$ docker pull mongo
$ docker run --name my-container-name -d mongo:latest
```

# Considerations

- Decided to go with MongoDB as it is more versatile and easier to set up and the requirements weren't too stringent requiring relationships or the use of a RDBMS
- Changed the base path of the LoanController just as a preference in order to have a more concrete separation of concerns by name.
- Added a configuration for MetricCalculator in order to make it more robust and scalable over time, plus it allows us to modify the key by Loan Type or another identifier other than the Bean name
- Added a few new endpoints to save to the database a generated Loan and to get all Loans saved in the database
- Access to Swagger-UI at http://localhost:8080/swagger-ui/
- All available endpoints
  - /api/loans/generate
  - /api/loans/{id}
  - /api/loans/all
  - /api/loans/find/max-monthly-payment
  - /api/metrics/
  - /api/metrics/{id}