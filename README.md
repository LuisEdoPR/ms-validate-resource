***

# WAES Project - Luis Eduardo Patiño

***

## Business Documentation (assumptions)

At the root of the project there is a folder called documentation, 
within it a PDF is attached where it explains in detail what was assumed, 
and a brief explanation of the proposed solution, also you can also click 
on the following link:

## ![Documentation](documentation/pdf.png) [Documentation (click here)](documentation/Assignment_Scalable_Web-Java-Luis_Eduardo_Patino.pdf)

***

## Starting

You need to do next steps to start with develop or run to this project.

## Pre-Requirements

#### Java configuration:

- Download  ***version 11*** of the JDk according to the operating
  system ([JDK list](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html))


- To verify the version of java, we need to execute the following command:
    ```sh
    $ java -version
    ```

#### Maven configuration:

- Install maven ([download](http://maven.apache.org/download.cgi)).
- Configure maven ([configure](https://maven.apache.org/install.html))


- To verify the version of maven, we need to execute the following command:
    ```sh
    $ mvn --version
    ```

***

## Run the API

Steps to run the API:

- We must install or update the necessary dependencies for 
  the API with the following commands:

    ```sh
    $ mvn update
    $ mvn clean install
    ```

- To run the project run the following command:

    ```sh
    $ mvn spring-boot:run
    ```

***

## OpenAPI Documentation (Swagger)

In order to see the endpoints of the services, you must go to 
the browser and open the following url:

[swagger-ui.html](http://localhost:9090/api/swagger-ui.html)

***

## Testing API

To run only tests without creating our application JAR/WAR/EAR file, 
we need to use the following commands:

  ```sh
    $ mvn clean test
  ```

***

## Used Technology

![Java](documentation/java.png) [Java](https://www.oracle.com/technetwork/java/index.html)

![Spring Boot](documentation/spring-boot.png) [Spring Boot](https://spring.io/projects/spring-boot)

![Cucumber: BDD Testing](documentation/cucumber.png) [Cucumber: BDD Testing](https://cucumber.io/)

![Maven](documentation/maven.png) [Maven](https://maven.apache.org/)

![Rest](documentation/rest.png) [Rest](https://es.wikipedia.org/wiki/Transferencia_de_Estado_Representacional)

![OpenApi](documentation/openapi.png) [OpenApi](https://www.openapis.org/)

![Lombok](documentation/lombok.png) [Lombok](https://projectlombok.org/)

***
***

