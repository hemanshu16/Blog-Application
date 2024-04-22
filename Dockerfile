FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/blogapplication-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/blogapplication-0.0.1-SNAPSHOT.jar.jar"]