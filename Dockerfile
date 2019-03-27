FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar purchaseswebservice-0.1.0.jar
ENTRYPOINT exec java -Dserver.port=8017 -jar purchaseswebservice-0.1.0.jar