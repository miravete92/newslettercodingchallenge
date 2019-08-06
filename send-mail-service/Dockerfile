FROM openjdk:12-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ENV MYSQL_HOST=127.0.0.1
COPY target/send-mail-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]