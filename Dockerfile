FROM openjdk:17-jdk-alpine

COPY test.jar /usr/src/app/test.jar

ENTRYPOINT ["java", "-jar", "test.jar", "--spring.profiles.active=prod"]

WORKDIR /usr/src/app