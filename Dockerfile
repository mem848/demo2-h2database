FROM openjdk:17
LABEL authors="laneymodlin"
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]