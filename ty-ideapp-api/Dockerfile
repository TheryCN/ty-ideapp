FROM openjdk:8-jdk-alpine

VOLUME /tmp

# Copy
ARG JAR_FILE=target
COPY ${JAR_FILE} ty-ideapp-api.jar

# Make port 8100 available to the world outside this container
EXPOSE 8100

# Run app
ENTRYPOINT ["java","-jar","/ty-ideapp-api.jar"]