FROM openjdk:17
WORKDIR /app
COPY target/users-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "users-0.0.1-SNAPSHOT.jar"]
