FROM alpine/java:21-jdk

WORKDIR /app

COPY target/MSTransactions-0.0.1-SNAPSHOT.jar MSTransactions.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "MSTransactions.jar"]