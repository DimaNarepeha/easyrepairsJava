FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN apt-get update && \
    apt-get install -y curl
RUN apt-get install -y maven
ENTRYPOINT ["sh", "-c", "mvn clean install && mvn spring-boot:run"]