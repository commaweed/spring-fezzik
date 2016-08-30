    ====================================================
    \_\_\  ______ _____ ______ ______ ______ ___   ____
    /_/_/ |      |     |      |      |      |   | /   /
    \_\_\ |    __|  ___|__   /|__   /|_    _|   |/   /
    /_/_/ |    __|  ___| /  /_  /  /_ _|  |_|       <
    \_\_\ |   |  |     |/     |/     |      |   |\   \
    /_/_/ |___|  |_____|______|______|______|___| \___\
    \ \ \       P2P Appliction - Version 1.0.0
    ====================================================

Linux VM setup:

    https://docs.docker.com/engine/installation/linux/centos/

Docker needs to be installed and running

    docker pull mongo

    docker run --name fezzik-mongo -p 27017:27017 -v ~/dev/data/dir:/data/db -d mongo
    OR
    docker run -it --name fezzik-mongo -p 27017:27017 -v /your/dir:/data/db -v /your/dir/mongod.conf:/etc/mongod.conf -d mongo --config /etc/mongod.conf

Run the Spring Boot App

    mvn clean spring-boot:run
    
    java -jar fezzik-<version>.jar spring-boot:run 
    java -jar fezzik-<version>.jar spring-boot:run -Dspring.profiles.active=docker

Test to see if it is running:

    localhost:8080
    Swagger: http://localhost:8080/swagger-ui.html
    HAL Browser: http://localhost:8080/browser/index.html
    
    localhost:9090/health
    localhost:9090/beans
    localhost:9090/dump
    localhost:9090/metric
    localhost:9090/info
    localhost:9090/trace

Run the build

    mvn clean package docker:build

    docker run --name fezzik-rest -p 8443:8443 -p 9443:9443 -e FEZZIK_HOME=/tmp -v ~/fezzik:/tmp --link fezzik-mongo:mongo  -d ets/fezzik-rest

Test to see if it is running:

    https://localhost:8443/
    Swagger: http://localhost:8443/swagger-ui.html
    HAL Browser: http://localhost:8443/browser/index.html
    
    https://localhost:9443/health
    https://localhost:9443/beans
    https://localhost:9443/dump
    https://localhost:9443/metric
    https://localhost:9443/info
    https://localhost:9443/trace

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

    Delete untagged images
        docker images -q --filter "dangling=true" | xargs docker rmi