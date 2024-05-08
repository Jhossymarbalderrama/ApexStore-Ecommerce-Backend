FROM amazoncorretto:17
MAINTAINER blackdev
COPY target/e-commerce-0.0.1-SNAPSHOT.jar e-commerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/e-commerce-0.0.1-SNAPSHOT.jar"]
