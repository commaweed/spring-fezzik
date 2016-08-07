    ====================================================
    \_\_\  ______ _____ ______ ______ ______ ___   ____
    /_/_/ |      |     |      |      |      |   | /   /
    \_\_\ |    __|  ___|__   /|__   /|_    _|   |/   /
    /_/_/ |    __|  ___| /  /_  /  /_ _|  |_|       <
    \_\_\ |   |  |     |/     |/     |      |   |\   \
    /_/_/ |___|  |_____|______|______|______|___| \___\
    \ \ \       P2P Appliction - Version 1.0.0
    ====================================================

Install Lombok Plugin

    


Docker needs to be installed and running

    docker pull mongo

    docker run --name fezzik-mongo -p 27010:27010 -v ~/dev/data/dir:/data/db -d mongo

Run the Spring Boot App

    mvn clean spring-boot:run
    
    java -jar fezzik-<version>.jav spring-boot:run 
    java -jar fezzik-<version>.jav spring-boot:run -Dspring.profiles.active=integration
    java -jar fezzik-<version>.jav spring-boot:run -Dspring.profiles.active=production

Run the build

    mvn clean package docker:build

    docker run --name fezzik-webapp -p 8080:8080 --link fezzik-mongo:mongo -d ets/fezzik

    docker run -it --name fezzik-webapp -p 8080:8080  -d ets/fezzik

Test to see if it is running:

    localhost:8080

Docker commands:

    Show running docker containers:
        docker ps

    Show list of docker images:
        docker images

    Stop a running container
        docker stop fezzik-webapp

    Start a container
        docker start fezzik-webapp

    Remove a container
        docker rm fezzik-webapp

    Log into mongo running in a container
        docker exec -it fezzik-mongo mongo admin
        
Swagger

    http://localhost:8080/swagger-ui.html
    
HAL Browser

    http://localhost:8080/browser/index.html