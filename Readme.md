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

    docker run --name fezzik-mongo -p 27017:27017 -v ~/dev/data/dir:/data/db -d mongo
    OR
    docker run -it --name fezzik-mongo -p 27017:27017 -v /your/dir:/data/db -v /your/dir/mongod.conf:/etc/mongod.conf -d mongo --config /etc/mongod.conf

Run the Spring Boot App

    mvn clean spring-boot:run
    
    java -jar fezzik-<version>.jav spring-boot:run 
    java -jar fezzik-<version>.jav spring-boot:run -Dspring.profiles.active=docker

Run the build

    mvn clean package docker:build

    docker run --name fezzik-rest -p 8443:8443 -p 9443:9443 --link fezzik-mongo:mongo  -d ets/fezzik

Test to see if it is running:

    localhost:8080
    localhost:9090

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