FROM openjdk:21-jdk-slim

# Specified from docker-compose.yml args
#ARG PORT 

RUN groupadd -r buddy_group && useradd -r -g buddy_group backend_usr

USER backend_usr

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

#EXPOSE ${PORT}

ENTRYPOINT ["java","-jar","/app.jar"]