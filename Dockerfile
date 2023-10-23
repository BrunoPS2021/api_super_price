FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 9001

COPY --from=build target/restApiAndroid-0.0.1-SNAPSHOT.jar apisuperprice.jar

ENTRYPOINT [ "java" , "-jar", "apisuperprice.jar" ]