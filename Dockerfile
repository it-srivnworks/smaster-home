FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:11
LABEL maintainer="srivn.com"
ADD /home/app/target/smaster-home-0.0.1-SNAPSHOT.jar smaster-home-0.0.1.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","smaster-home-0.0.1.jar"]
