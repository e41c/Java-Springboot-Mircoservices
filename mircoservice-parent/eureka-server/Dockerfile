#Build Image

#Base Image
FROM gradle:8-jdk17-alpine as builder

#copy application files from host machine to the image file system
COPY --chown=gradle:gradle . /home/gradle/src

#set the directory in the image for all subsequent commands
WORKDIR /home/gradle/src

#Run gradle build within the container - NOT the host machine for RUN commands, the command only apply
#to images RUN gradle build ----no-daemon -x test --- this did NOT WORK
RUN gradle build -x test

# Package Stage

FROM openjdk:17-alpine

#Create directory within the container
RUN mkdir /app

#Copyt the applivation files from the host machine to the image filesystem
COPY --from=builder  /home/gradle/src/build/libs/*.jar app/eureka-server.jar



EXPOSE 8761

#Execute the follow 1 command after the container stasrts
ENTRYPOINT ["java","-jar","/app/eureka-server.jar"]