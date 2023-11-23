FROM openjdk:11-jre-slim
VOLUME /tmp
ADD target/Osa03_05.NamesAndAddresses-1.0-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]