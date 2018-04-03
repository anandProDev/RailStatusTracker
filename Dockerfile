FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/RailStatusTracker-1.0-SNAPSHOT.jar railStatusTracker.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /railStatusTracker.jar
EXPOSE 8080