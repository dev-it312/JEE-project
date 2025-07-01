# Kamel One Microservices Project

This project demonstrates a microservice architecture using Spring Boot and Spring Cloud.

## Modules

The project consists of the following microservices:

*   **config-service**: Spring Cloud Config Server (Port: 8888) - Manages externalized configurations for other services. (Native backend, loads from `config-service/src/main/resources/config-repo`)
*   **directory-service**: Spring Cloud Netflix Eureka Server (Port: 8761) - Service discovery and registration.
*   **gateway-service**: Spring Cloud Gateway (Port: 8080) - API Gateway to route requests to backend services.
*   **product-service**: Sample business logic service (Port: 8081, or as configured by Config Server). Demonstrates H2 database integration and configuration client.
*   **customer-service**: Sample business logic service (Port: 8082, needs `application.properties` or config in Config Server)
*   **inventory-service**: Sample business logic service (Port: 8083, needs `application.properties` or config in Config Server)
*   **supplier-service**: Sample business logic service (Port: 8084, needs `application.properties` or config in Config Server)
*   **stock-service**: Sample business logic service (Port: 8085, needs `application.properties` or config in Config Server)
*   **billing-service**: Sample business logic service (Port: 8086, needs `application.properties` or config in Config Server)

## Prerequisites

*   Java 11 or higher
*   Apache Maven 3.6.x or higher

## How to Build

Navigate to the root directory of each service (e.g., `kamel_one/config-service`) and run:

```bash
mvn clean install
```

Alternatively, you can build all services from the `kamel_one` parent directory if a parent `pom.xml` is set up to manage all modules (not implemented in this version). For now, build each service individually.

## How to Run

Services should be started in the following order due to dependencies:

1.  **Config Server (`config-service`)**
2.  **Directory Server / Eureka Server (`directory-service`)**
3.  **Other Microservices** (e.g., `product-service`, `customer-service`, `gateway-service`, etc.)

**1. Run Config Server:**
Navigate to `kamel_one/config-service/`
```bash
mvn spring-boot:run
# Or run the JAR: java -jar target/config-service-0.0.1-SNAPSHOT.jar
```
Wait for it to start up (check logs for port 8888).

**2. Run Directory (Eureka) Server:**
Navigate to `kamel_one/directory-service/`
```bash
mvn spring-boot:run
# Or run the JAR: java -jar target/directory-service-0.0.1-SNAPSHOT.jar
```
Wait for it to start up. You can access the Eureka dashboard at `http://localhost:8761`.

**3. Run Product Service (Example):**
Navigate to `kamel_one/product-service/`
```bash
mvn spring-boot:run
# Or run the JAR: java -jar target/product-service-0.0.1-SNAPSHOT.jar
```
This service will fetch its configuration from the Config Server and register with Eureka.
Check Eureka dashboard to see if `PRODUCT-SERVICE` is registered.
Access H2 console for product-service: `http://localhost:8081/h2-console` (JDBC URL: `jdbc:h2:mem:productdb`, User: `sa`, Pwd: `(blank)`)
Test endpoint: `http://localhost:8081/products/sample` or `http://localhost:8081/products/all`

**4. Run Gateway Service:**
Navigate to `kamel_one/gateway-service/`
```bash
mvn spring-boot:run
# Or run the JAR: java -jar target/gateway-service-0.0.1-SNAPSHOT.jar
```
This service will register with Eureka.
Test product-service through gateway: `http://localhost:8080/api/products/sample` or `http://localhost:8080/api/products/all`

**5. Run Other Services (Customer, Inventory, etc.):**
These services currently lack `src/main/resources/application.properties` or specific configurations in the Config Server for ports, Eureka registration, etc. You will need to add these to run them.
Example for `customer-service` (create `kamel_one/customer-service/src/main/resources/application.properties`):
```properties
server.port=8082
spring.application.name=customer-service
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
# If using config server, add bootstrap.properties:
# spring.application.name=customer-service
# spring.cloud.config.uri=http://localhost:8888
```
Then run:
Navigate to `kamel_one/customer-service/`
```bash
mvn spring-boot:run
```

## Accessing Services

*   **Config Server**: `http://localhost:8888`
    *   Example: `http://localhost:8888/product-service/default` (to see config for product-service)
*   **Eureka Dashboard**: `http://localhost:8761`
*   **API Gateway**: `http://localhost:8080`
*   **Product Service (direct)**: `http://localhost:8081`
    *   Sample: `http://localhost:8081/products/sample`
    *   Get All: `http://localhost:8081/products/all`
    *   POST (create, with JSON body `{"name":"Test", "description":"Test desc", "price":10.0}`): `http://localhost:8081/products`
    *   H2 Console: `http://localhost:8081/h2-console`
*   **Product Service (via Gateway)**:
    *   Sample: `http://localhost:8080/api/products/sample`
    *   Get All: `http://localhost:8080/api/products/all`
    *   POST (create, with JSON body): `http://localhost:8080/api/products`

## Further Development

*   Add `application.properties` or Config Server configurations for all services (ports, names, Eureka settings).
*   Implement `bootstrap.properties` for all services that need to connect to the Config Server.
*   Complete REST controllers and business logic for all services.
*   Implement inter-service communication (e.g., using RestTemplate, WebClient, or FeignClient).
*   Add security (e.g., Spring Security, OAuth2).
*   Set up a parent POM to manage common dependencies and build all modules at once.
*   Add Dockerfiles and a `docker-compose.yml` for easier deployment.
*   Implement more robust error handling and logging.
*   Add unit and integration tests.
```
