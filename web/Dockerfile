FROM openjdk:8u201-jdk-alpine3.9
#FROM registry.lisong.pub:5000/sunrise/java:8-jdk-211
VOLUME /tmp
#ADD hosts tmp/

ADD target/web-0.0.1-SNAPSHOT.jar app.jar

ENV LANG C.UTF-8
#CMD cat /tmp/hosts >> /etc/hosts;cat /etc/hosts
ENTRYPOINT java -jar app.jar
