# User-Management-Portal

## How to Run this project
#### 1. Open this project on your ide.
#### 2. Connect this project to mySQL server and add this query to your mySQL server:


CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `contactNo` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=803 DEFAULT CHARSET=utf8mb4 
COLLATE=utf8mb4_0900_ai_ci


#### 3. Add username and password in application.properties file of userManagement microservice.

#### 4. There are 4 microservices and you need to open application file of each of this microservices and run first gateway then authentication thereafter userManagement and then registryService.

#### 5. Go to https://localhost:8843 and you are done!!!.
