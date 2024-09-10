# Arithmetic Calculator REST API

## Description

This project is a REST API for an arithmetic calculator developed using Spring Boot. It provides endpoints for performing arithmetic operations and utilizes JWT for authentication and authorization. The API is configured to run on AWS Elastic Beanstalk with an API Gateway for handling HTTP requests.

## System Design

![Untitled-2024-09-04-2355](https://github.com/user-attachments/assets/95826135-96d0-4c43-8ff5-49bfed483d6e)

## MER (Entity-Relationship model)

![Entity Relationship Diagram](https://github.com/user-attachments/assets/3e2668df-1e1d-4d47-a13a-8b2db1a883f8)

## Prerequisites

- **Java 17**: Ensure you have JDK 17 installed.
- **Maven**: Make sure Apache Maven is installed to handle dependencies and build the project.

## Running the Project Locally

Follow these steps to run the project locally:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/your-repository.git
   cd your-repository
   ```

2. **Build the Project**

   Use Maven to build the project. This will compile the code and package it into an executable JAR file.

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   You can run the application using the Spring Boot Maven plugin. This will start the application on the default port (8080).

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, you can run the JAR file directly:

   ```bash
   java -jar target/arithmetic-calculator-0.0.1-SNAPSHOT.jar
   ```

4. **Access the Application**

   Once the application is running, you can access the API at:

   ```
   http://localhost:8080/api/v1/authenticate
   ```

   Replace `/authenticate` with the appropriate endpoint as needed.

## Configuration

The application uses an H2 in-memory database for development and testing. You can configure database settings in `src/main/resources/application.properties`.

Example configuration:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Deployment

The API is deployed on AWS Elastic Beanstalk and is accessible via API Gateway.

- **Elastic Beanstalk URL**: `http://arithmetic-calculator-env-1.eba-xnifh8jy.eu-north-1.elasticbeanstalk.com`
- **API Gateway URL**: `https://r9anf27jf9.execute-api.eu-north-1.amazonaws.com/prod/arithmetic-calculator/auth`

## Testing

Run unit tests using Maven:

```bash
mvn test
```

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Apache Maven](https://maven.apache.org/)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [AWS API Gateway](https://aws.amazon.com/api-gateway/)

---
