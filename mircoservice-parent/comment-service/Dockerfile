FROM gradle:8-jdk17-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test

FROM openjdk:17-jdk-alpine


RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/user-service.jar


ENV ENV SPRING_DATASOURCE_USERNAME=rootadmin \
    ENV SPRING_DATASOURCE_PASSWORD=password


EXPOSE 8087

ENTRYPOINT ["java","-jar","/app/user-service.jar"]