FROM openjdk:8-jre-alpine

ADD target/red-two-service-1.0.jar /app.jar

VOLUME /tmp
EXPOSE 8080
CMD java -jar /app.jar