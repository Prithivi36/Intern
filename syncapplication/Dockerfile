FROM openjdk:21

WORKDIR /app/

COPY ./target/syncapplication-0.0.1-SNAPSHOT.jar /app/spring.jar

EXPOSE 8080

CMD ["java","-jar","spring.jar"]