FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE

COPY target/${JAR_FILE} crimetivitystoreservice.jar

CMD ["java","-jar", "crimetivitystoreservice.jar"]

VOLUME /var/lib/crimetivitystoreservice