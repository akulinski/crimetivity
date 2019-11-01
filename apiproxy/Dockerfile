FROM openjdk:11-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE
ARG JAVA_PORT

COPY target/${JAR_FILE} apiproxy.jar

CMD ["java","-jar", "apiproxy.jar"]

VOLUME /var/lib/apiproxy

EXPOSE ${JAVA_PORT}