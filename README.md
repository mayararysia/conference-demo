# RESTful  Application with Spring Boot: Conference Demo

Training with Opinionated Framework: [Spring Boot](https://spring.io/projects/spring-boot).

## About this Project

* Spring Boot and Spring Data JPA to do CRUD operations against a PostgreSQL database.
* Based at Spring Framework course of [Pluralsight] (https://www.pluralsight.com/) and contains my adaptations.
* **Development Environment: IntelliJ IDEA Version 2018.3.6** 
* 100% pure Java Code

## Why?

This project is part of my studies portfolio, so, feel free to contribute with improvements and updates.

Email-me: mayara.ryzia@gmail.com | mral1@ifal.edu.br

Connect with me at [Medium](https://medium.com/@mayararysia) and [LinkedIn](https://www.linkedin.com/in/rysia/).

It's free!

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

## Screens

![starting](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/starting-main.png)
![postman](https://raw.githubusercontent.com/mayararysia/conference-demo/master/screenshots/postman.png)

