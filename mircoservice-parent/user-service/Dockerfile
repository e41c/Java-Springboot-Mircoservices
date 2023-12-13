# Build Stage
FROM gradle:8.3-jdk17 AS builder


#ARG GRADLE_VERSION=8.5

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test

# Run Stage
FROM openjdk:17-jdk-alpine

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/user-service.jar

ENV SPRING_DATASOURCE_USERNAME=rootadmin \
    SPRING_DATASOURCE_PASSWORD=password

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
