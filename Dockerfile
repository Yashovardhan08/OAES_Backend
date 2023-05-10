FROM openjdk:19
ADD target/db-service.jar db-service.jar
ENTRYPOINT ["java", "-jar","db-service.jar"]
EXPOSE 8696