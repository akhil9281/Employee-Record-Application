FROM openjdk:20-jdk
LABEL mainainer="Akhil Kumar"
ADD target/spring-boot-backend-application-0.0.1-SNAPSHOT.jar spring-boot-backend-application-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-backend-application-0.0.1-SNAPSHOT.jar"]

