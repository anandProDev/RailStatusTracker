#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD distributions/RailStatusTracker-1.0-SNAPSHOT.jar railStatusTracker.jar
#ENV JAVA_OPTS=""
#ENTRYPOINT exec java -jar /railStatusTracker.jar
#EXPOSE 8080

#
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8081
