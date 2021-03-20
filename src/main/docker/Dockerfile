FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eshare-0.0.1-SNAPSHOT.jar.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]