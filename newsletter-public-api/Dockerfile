FROM openjdk:12-jdk-alpine
VOLUME /tmp
COPY target/newsletter-public-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]