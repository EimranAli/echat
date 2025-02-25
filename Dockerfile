FROM openjdk:21
VOLUME /tmp
COPY target/echat-0.0.1-SNAPSHOT.jar echat.jar
EXPOSE 8080
ENTRYPOINT exec java -jar echat.jar
