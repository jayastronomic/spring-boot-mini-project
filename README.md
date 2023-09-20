# Spring Boot Mini Project

## Project Description

This Spring Boot Mini Project showcases the development of a monolithic backend using Spring Boot, integrating various modules to create a RESTful web API. The application utilizes a Postgres database, and it runs on the Tomcat server. The primary goal is to implement a secure and functional backend for a library application.

## Tools and Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT Tokens
- PostgreSQL
- Tomcat Server

## Approach

- **Database Models**: The project includes three main models: `User`, `Genre`, and `Book` to represent users, book genres, and library books.

- **Spring Profiles**: Environment settings are configured using Spring Profiles to manage different deployment environments.

- **Security**: The application leverages Spring Security and JWT tokens to ensure authentication and authorization for API endpoints, maintaining robust security.

- **CRUD Operations**: At least one API endpoint supports complete CRUD operations (Create, Read, Update, Delete), with additional endpoints designed to fulfill specific business use cases.

- **RESTful Conventions**: CRUD routes adhere to RESTful conventions, providing standardized and intuitive API endpoints.

- **Exception Handling**: Exception handling is implemented to gracefully manage errors, providing meaningful error messages to users.

- **MVC Design**: The codebase follows the MVC (Model-View-Controller) design pattern with separate controllers and services.

- **Documentation**: Comprehensive documentation is provided, including docstrings and inline comments, to enhance code readability and maintainability.

- **Version Control**: The project is managed through Git, with around 90 commits showcasing the development progress.

## User Stories

[Link to User Stories](USER_STRORIES.md)

## Entity-Relationship Diagram (ERD)

[Link to User Stories](Library%20ERD.png)


## Installation Instructions

1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory: `cd spring-boot-mini-project`
3. Build and run the project using Maven: `./mvnw spring-boot:run`

Please ensure you have Java and Maven installed on your system before running the project.

## Acknowledgments

I would like to give credit to my instructors and classmates for their support and contributions during the project development. Their guidance and collaboration were instrumental in the project's success.
