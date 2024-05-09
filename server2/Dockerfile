FROM openjdk:21

WORKDIR /app/

COPY ./target/server2-0.0.1-SNAPSHOT.jar /app/spring.jar

EXPOSE 8081

CMD ["java","-jar","spring.jar"]