
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




