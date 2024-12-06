#FROM ubuntu:latest
#LABEL authors="wangjiawei"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:24-ea-25-jdk-oraclelinux9

ADD target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/.urandom -jar /app.jar"]
