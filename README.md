## MidlandDigital TestApp

## Tools Used 
- Java 11 
- Spring Boot 2.6.3
- Gradle build tool 
- H2 in memory DB
- docker
- git , github 

## How to run this app on your local environment 


- Pull the latest docker image from Dockerhub [here](https://hub.docker.com/r/hsolomondocker/midlanddigital_testapp)

     Or simply run this command to pull the latest image tag

    `docker pull hsolomondocker/midlanddigital_testapp

    Run this command to confirm if the image is loaded to your host machine 

    ` docker image ls `

    you should see vip docker image listed `hsolomondocker/midlanddigital_testapp:latest `
  
- Run this command to launch the conainter with TestAPP
   
   ` docker run -it -p9090:8080 hsolomondocker/midlanddigital_testapp:latest  `
   
    **Make sure that port 9090 is open in your machine and also not blocked by any firewall setup you may have**
   
- You can Run `docker ps ` to verify that the container is running on your local docker engine
- Open your browser and go to (http://localhost:9090/api-docs.html) you should see swagger api doc page

## How to test the functionalities 
