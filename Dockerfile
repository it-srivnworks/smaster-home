FROM openjdk:11
LABEL maintainer="srivn.com"
ADD target/smaster-home-0.0.1-SNAPSHOT.jar smaster-home-0.0.1.jar
ENTRYPOINT ["java","-jar","smaster-home-0.0.1.jar"]