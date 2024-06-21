FROM openjdk:21-jdk-slim

RUN groupadd -r buddy_group && useradd -r -g buddy_group backend_usr

USER backend_usr

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]