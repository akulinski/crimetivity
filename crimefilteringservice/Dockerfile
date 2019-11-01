FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE

COPY target/${JAR_FILE} crimefilteringservice.jar

CMD ["java","-jar", "crimefilteringservice.jar"]

VOLUME /var/lib/apiproxy
