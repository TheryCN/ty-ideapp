FROM hypriot/rpi-java

VOLUME /tmp

# Copy
COPY target/ty-ideapp-api.jar ty-ideapp-api.jar

# Make port 8100 available to the world outside this container
EXPOSE 8100

# Run app
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","/ty-ideapp-api.jar"]