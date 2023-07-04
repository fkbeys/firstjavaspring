FROM openjdk:20-jdk
WORKDIR /app
COPY target/kayaspring-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "/app/kayaspring-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:latest
#WORKDIR /app
#COPY target/kayaspring-0.0.1-SNAPSHOT.jar kayaspring-0.0.1-SNAPSHOT.jar
#EXPOSE 4444
#ENTRYPOINT ["java","-jar","kayaspring-0.0.1-SNAPSHOT.jar"]


