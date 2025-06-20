FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/cardgame-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]