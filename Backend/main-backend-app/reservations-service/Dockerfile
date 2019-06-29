FROM anapsix/alpine-java
MAINTAINER Milos Krstic <krsticm1996@gmail.com>
ADD target/reservations-service-0.0.1-SNAPSHOT.jar reservations-service.jar
ENTRYPOINT ["java", "-jar", "/reservations-service.jar"]
EXPOSE 8200