FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE

COPY target/${JAR_FILE} crimelocationservice.jar

CMD ["java","-jar", "crimelocationservice.jar"]

VOLUME /var/lib/crimelocationservice