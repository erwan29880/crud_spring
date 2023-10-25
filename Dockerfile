FROM openjdk:17-alpine

COPY demo1_1.jar demo1.jar
EXPOSE 8080
CMD ["java", "-jar", "demo1.jar"]