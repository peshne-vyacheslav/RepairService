## Примеры запуска с помощью spring-boot-maven-plugin
* mvn spring-boot:run
* mvn spring-boot:run -Dspring-boot.run.profiles=initdb
* mvn spring-boot:run -Dspring-boot.run.profiles=dev,initdb

## Примеры сбокрки и запуска jar файла(профили также опциональны) 
1. mvn clean install
2. java -Dspring.profiles.active=dev,initdb -jar target/repair-service-0.0.1-SNAPSHOT.jar

### Доступные профили
* initdb инициализировать схему бд и заполнить тестовыми данными
* dev использовать в качестве бд h2 

Можно задать перемеренные окружения или воспользоваться значениями по умолчанию:
* DB_URL=jdbc:postgresql:repair-service
* DB_USER=postgres
* DB_PASSWORD=postgres

Пример:
mvn spring-boot:run -Dspring-boot.run.arguments="--DB_URL=jdbc:postgresql:repair-service 
    --DB_USER=postgres --DB_PASSWORD=postgres"

## Юзеры в бд
* admin / admin
* user / user


## Доступные url:
* http://localhost:8443/repair-service/
* http://localhost:8443/repair-service/users
