FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app

COPY . .

RUN gradle :bootloader:bootJar -x test

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/bootloader/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
