## Running a Spring Boot Application with MySql on Docker

## Create a Spring Boot Application

## Use environment variables for MySQL properties 


## Creating Docker image

## Method 1 :  

run as maven build spring-boot:build-image

## Method 2

### Create the jar file

### Create a Dockerfile

```Dockerfile

FROM openjdk:17-jdk-slim
WORKDIR /
ADD target/spring-boot-app.jar app.jar
CMD java -jar app.jar

```


### Build the image

```bash
docker build -t spring-boot-app .

```


## Creating a network :

```bash
docker network create verizon-net
```

## Riunning Mysql
```bash
docker run -p 3306:3306 --network verizon-net --name mysql1 -e MYSQL_ROOT_PASSWORD=password  -e MYSQL_DATABASE=verizon_db -d mysql

```

## Running Spring Boot

```bash
docker run -p 8000:8000 --network verizon-net --name spring-boot-app -e MYSQL_HOST=mysql1 -e MYSQL_PORT=3306 -e MYSQL_DATABASE=verizon_db -e MYSQL_USER=root -e MYSQL_PASSWORD=password -d spring-boot-app

```



## Image Names

product-service:0.0.1-SNAPSHOT

cart-service:0.0.1-SNAPSHOT

api-gateway:0.0.1-SNAPSHOT

eureka-server:0.0.1-SNAPSHOT






