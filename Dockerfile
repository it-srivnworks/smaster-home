FROM openjdk:11
LABEL maintainer="srivn.com"
ADD target/smaster-home-0.0.1-SNAPSHOT.jar smaster-home-0.0.1.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","smaster-home-0.0.1.jar"]