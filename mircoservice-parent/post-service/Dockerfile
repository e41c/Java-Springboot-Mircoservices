
#--------------
#-- Dockerfile ----


#----------------
#-- Build Stage --
#----------------


#-- Base Image --
FROM gradle:8-jdk17-alpine AS build

#-- Copy the application files from the host to the container's filesystem --
COPY --chown=gradle:gradle . /home/gradle/src

#-- Set the working directory within image , for executing future commands
WORKDIR /home/gradle/src

#run grade build within the container -- NOT host machine --
RUN gradle build -x test

#----------------
#Paackage Stage --
#----------------

FROM openjdk:17-jdk-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/post-service.jar

ENV MONGO_DB_USERNAME=rootadmin \
    MONOGO_DB_PWD=password

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/post-service.jar"]