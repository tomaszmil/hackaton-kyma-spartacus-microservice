FROM maven:3.6-jdk-8 AS builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn install

FROM openjdk:8-jdk-alpine
COPY --from=builder /usr/src/app/target/purchaseswebservice-0.1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

