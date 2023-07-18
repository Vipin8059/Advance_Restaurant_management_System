# Restaurant-API
This project is a simple implementation of a Restaurant API using Spring Boot and Jakarta Persistence. The API provides basic CRUD operations for managing restaurants and their menus.

## Endpoints
The following endpoints are available:
### User
- **Post /user/signUp -** Post a new User
- **Post /order -** Order food items from the menu.
- **POST /user/signIn -** Creates a new authentication token for user
- **DELETE /api/User/signOut -** Deletes an authentication token

### Admin
- **Post /admin/signUp -** Post a new admin
- **POST /admin/signIn -** Creates a new authentication token for admin
- **POST /foodItem -** Creates a new foodItem
- **DELETE /foodItem -** Deletes an foodItem


## Model
The project has two main entities:
### Order
- id: Long
- User: UserId
- Status: foodStatus
- quantity: Integer
- foodItem: List<Food>

### Food
- id: Long
- title: String
- description: Double
- admin: Admin

## Technologies
The following technologies were used in this project:
- Spring Boot
- Jakarta Persistence
- Lombok
- Maven
- java
- Mysql

## Project Summary
This is a Java Spring Boot API for managing order and their foodItems. The API includes endpoints for CRUD operations on User, order and foods, as well as validation of input data. It also includes integration with a MySQL database for data persistence. 
