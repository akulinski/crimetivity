FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE

COPY target/${JAR_FILE} pointsaftyservice.jar

CMD ["java","-jar", "pointsaftyservice.jar","-Dspring.profiles.active=kubernetes"]

VOLUME /var/lib/pointsaftyservice