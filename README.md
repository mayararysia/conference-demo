# RESTful  Application  Programming Interface (API) with Spring Boot: Conference Demo

Training with [Spring Boot](https://spring.io/projects/spring-boot)'s opinionated framework.

## About this Project

* Spring Boot and Spring Data JPA to do CRUD operations in the PostgreSQL database.
* Based on the Spring Framework course of [Pluralsight](https://www.pluralsight.com/) and contains my adaptations.
* **Development Environment: IntelliJ IDEA Version 2018.3.6** 
* 100% pure Java Code
* **Project Type:** Maven Project
* **Group:** `com.rysia.conferencedemo`
* **Dependencies:** Web, JPA, PostgreSQL, Tomcat, FlywayDB, Springfox

## Why?

This project is part of my studies portfolio, so, feel free to contribute.

Email-me: mayara.ryzia@gmail.com | mral1@ifal.edu.br

Connect with me at [LinkedIn](https://www.linkedin.com/in/rysia/), [Medium](https://medium.com/@mayararysia) and [Dev.to](https://dev.to/mayararysia).

## Requirements

- Java JDK 11
- PostgreSQL version 12.2 (database name: conference)
- [Postman](https://www.postman.com/downloads/)
- Your favorite IDE
- Maven 3+

## How to use it?

* Clone the project or download
* To Import the Project into IntelliJ or Eclipse as a Maven Project
* Database configuration:
    * You can put your  database user  and database password  in the `config/PersistenceConfiguration.java` class
    * You can use the database configuration in `resources/application.properties`
        * Remove comments from the data source
        * Create the variables in `Menu-> Run-> Edit Configurations-> Environment variables` **(IntelliJ)**
        * Comment the data in the `config/PersistenceConfiguration.java` class
* Open Postman and test the routes on Port 5050. For example `http://localhost:5050/`

## **You can test my RESTful application with these routes:**
   
   * [Swagger Documentation](https://apirest-conference.herokuapp.com/swagger-ui.html)
   * [Swagger API Docs](https://apirest-conference.herokuapp.com/v2/api-docs)
   * [HOME](https://apirest-conference.herokuapp.com/)
   * [/sessions](https://apirest-conference.herokuapp.com/api/v1/sessions)
   * [/speakers](https://apirest-conference.herokuapp.com/api/v1/speakers)
   * [/attendees](https://apirest-conference.herokuapp.com/api/v1/attendees)
   * [/pricing/categories](https://apirest-conference.herokuapp.com/api/v1/pricing/categories)
   * [/tickets](https://apirest-conference.herokuapp.com/api/v1/tickets)
   * [/slots](https://apirest-conference.herokuapp.com/api/v1/slots)
   * [/tags](https://apirest-conference.herokuapp.com/api/v1/tags)
   * [/workshops](https://apirest-conference.herokuapp.com/api/v1/workshops)
   * [/ticket/types](https://apirest-conference.herokuapp.com/api/v1/ticket/types)
   * [/attendees/tickets](https://apirest-conference.herokuapp.com/api/v1/attendees/tickets)
   * [/discount/codes](https://apirest-conference.herokuapp.com/api/v1/discount/codes)
   * [/sessions/schedules](https://apirest-conference.herokuapp.com/api/v1/sessions/schedules)
    
## Screens

*API DOCUMENTATION*

![documentation](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/swagger-ui.png)


*LOCALHOST*

![starting](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/starting-main.png)
![postman](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/postman-v2.png)

*CLOUD DEPLOYMENT*


![heroku](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/heroku.png)

*WAR DEPLOYMENT*


![container](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/container-v2.png)


*ENTITY RELATIONSHIP DIAGRAM*

![ER-model](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/er-diagram.png)
