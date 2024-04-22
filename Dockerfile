FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/blog.jar /opt/app
CMD ["java", "-jar", "/opt/app/blog.jar"]