# RESTful  Application  Programming Interface (API) with Spring Boot: Conference Demo

Training with Opinionated Framework: [Spring Boot](https://spring.io/projects/spring-boot).

## About this Project

* Spring Boot and Spring Data JPA to do CRUD operations in the PostgreSQL database.
* Based at Spring Framework course of [Pluralsight](https://www.pluralsight.com/) and contains my adaptations.
* **Development Environment: IntelliJ IDEA Version 2018.3.6** 
* 100% pure Java Code

## Why?

This project is part of my studies portfolio, so, feel free to contribute.

Email-me: mayara.ryzia@gmail.com | mral1@ifal.edu.br

Connect with me at [Medium](https://medium.com/@mayararysia) and [LinkedIn](https://www.linkedin.com/in/rysia/).

## Requirements

- Java JDK 11
- PostgreSQL version 12.2 (database name: conference)
- [Postman](https://www.postman.com/downloads/)
- Your favorite IDE
- Maven 3+

## How to use?

* Clone the project or download
* Run the sql files from the **postgresql** folder in an administration tool of your choice
* To Import the Project into IntelliJ or Eclipse as a Maven Project
* Database configuration:
    * You can put your  database user   and database password  in the `config/PersistenceConfiguration.java` class
    * You can use the database configuration in `resources/application.properties`
        * Remove comments from the datasource
        * Create the variables in `Menu-> Run-> Edit Configurations-> Environment variables` **(IntelliJ)**
        * Comment the data in the `config/PersistenceConfiguration.java` class
* Open Postman and test the routes on Port 5050. For example: `http://localhost:5050/`

* **You can test my RESTful application with these routes:**
    * [HOME](https://conference-app-spring-boot.herokuapp.com/)
    * [/sessions](https://conference-app-spring-boot.herokuapp.com/api/v1/sessions)
    * [/speakers](https://conference-app-spring-boot.herokuapp.com/api/v1/speakers)
    * [/attendees](https://conference-app-spring-boot.herokuapp.com/api/v1/attendees)
    * [/pricings](https://conference-app-spring-boot.herokuapp.com/api/v1/pricings)
    * [/tickets](https://conference-app-spring-boot.herokuapp.com/api/v1/tickets)
    * [/slots](https://conference-app-spring-boot.herokuapp.com/api/v1/slots)
    * [/tags](https://conference-app-spring-boot.herokuapp.com/api/v1/tags)
    * [/workshops](https://conference-app-spring-boot.herokuapp.com/api/v1/workshops)
    * [/tickets/types](https://conference-app-spring-boot.herokuapp.com/api/v1/tickets/types)
    * [/attendees/tickets](https://conference-app-spring-boot.herokuapp.com/api/v1/attendees/tickets)
    * [/discount/codes](https://conference-app-spring-boot.herokuapp.com/api/v1/discount/codes)
    * [/sessions/schedules](https://conference-app-spring-boot.herokuapp.com/api/v1/sessions/schedules)
    
## Screens

*LOCALHOST*


![starting](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/starting-main.png)
![postman](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/postman.png)

*CLOUD DEPLOYMENT*


![heroku](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/heroku.png)

*WAR DEPLOYMENT*


![container](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/container.png)
