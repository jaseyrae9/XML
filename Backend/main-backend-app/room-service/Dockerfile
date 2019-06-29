FROM anapsix/alpine-java
MAINTAINER Milos Krstic <krsticm1996@gmail.com>
ADD target/room-service-0.0.1-SNAPSHOT.jar room-service.jar
ENTRYPOINT ["java", "-jar", "/room-service.jar"]
EXPOSE 8400