FROM openjdk:8-jdk-alpine
EXPOSE 8080:8080
COPY ./target/homework10-1.0.war /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java","-jar","homework10-1.0.war"]