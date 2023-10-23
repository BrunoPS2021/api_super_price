FROM maven:3.8.5-openjdk-17

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build target/restApiAndroid-0.0.1-SNAPSHOT.jar apisuperprice.jar

EXPOSE 9001

ENTRYPOINT [ "java" , "-jar", "apisuperprice.jar" ]