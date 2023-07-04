FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/kayaspring-0.0.1-SNAPSHOT.jar kayaspring-0.0.1-SNAPSHOT.jar
EXPOSE 4444
ENTRYPOINT ["java","-jar","kayaspring-0.0.1-SNAPSHOT.jar"]
