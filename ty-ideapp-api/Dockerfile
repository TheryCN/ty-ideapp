FROM openjdk:8-jdk-alpine

VOLUME /tmp

# Copy
COPY target/ty-ideapp-api.jar .

# Make port 8100 available to the world outside this container
EXPOSE 8100

# Run app
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/ty-ideapp-api.jar"]