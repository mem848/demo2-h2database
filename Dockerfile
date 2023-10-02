FROM openjdk:17
LABEL authors="laneymodlin"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]