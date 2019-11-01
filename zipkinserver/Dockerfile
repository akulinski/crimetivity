FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE

COPY target/${JAR_FILE} zipkinserver.jar

CMD ["java","-jar", "zipkinserver.jar"]

VOLUME /var/lib/pointsaftyservice