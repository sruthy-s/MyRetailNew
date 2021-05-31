FROM maven as builder
WORKDIR /app

COPY ./src /app/src
COPY ./pom.xml /app/pom.xml

RUN cd /app
RUN mvn clean install

FROM openjdk:8-jdk-alpine
COPY --from=builder /app/target/myretail-*.jar ./myretail.jar

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-jar","myretail.jar"]
