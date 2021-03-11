# Getting Started
###Description
UgroMart is a platform connecting customers to local market vendors. Users can sign up, view market vendors and purchase local produce like food, fruits and vegetables. UgroMart is an end to end solution that handles payments, deliveries, product and order management.

###Architecture
- The platform is built basing an Event Driven Microservices architecture Using Spring Cloud, RabbitMq for interprocess communication
Much as Java/SpringBoot is used, the platform microservices should be technology agnostic since RabitMq is hosted separately
- The api is secured using JWT alongside spring security

###Documentation
The api layer(gateway to the platform) is documentated using swagger2 and can be accessed upon running this service on
[Swagger Documentation](http://localhost:8080/swagger-ui/)  , replace localhost with your app url, and 8080 with your app port

###Setup
- [Install Java 8 or above](https://java.com/en/download/help/download_options.html) 

- clone the repo(Switch to master branch, it is more stable)

- cd in the root folder of the app in your terminal or
- run the following command in your terminal or console
    ```./mvnw clean package spring-boot:run -DskipTests=true```
- Refer to the swagger documentation above for how to use the rest api

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0-M2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.0-M2/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-security)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-amqp)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

