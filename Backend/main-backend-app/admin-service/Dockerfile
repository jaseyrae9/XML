FROM anapsix/alpine-java
MAINTAINER Milos Krstic <krsticm1996@gmail.com>
ADD target/admin-service-0.0.1-SNAPSHOT.jar admin-service.jar
ENTRYPOINT ["java", "-jar", "/admin-service.jar"]
EXPOSE 8100
