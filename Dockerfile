FROM amazoncorretto:17
LABEL maintainer="blackjhossy"
COPY e-commerce-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
