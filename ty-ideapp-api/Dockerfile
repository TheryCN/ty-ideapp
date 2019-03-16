#FROM openjdk:8-jdk-alpine

#VOLUME /tmp

# Copy
#COPY target/ty-ideapp-api.jar ty-ideapp-api.jar

# Make port 8100 available to the world outside this container
#EXPOSE 8100

# Run app
#ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","/ty-ideapp-api.jar"]


FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# Make port 8100 available to the world outside this container
EXPOSE 8100

# Run app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-cp","app:app/lib/*","com.github.therycn.tyideapp.TyIdeappApplication"]