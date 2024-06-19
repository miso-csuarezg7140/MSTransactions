FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY target/MSTransactions-0.0.1-SNAPSHOT.jar MSTransactions.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "MSTransactions.jar"]