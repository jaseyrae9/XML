FROM anapsix/alpine-java
MAINTAINER Milos Krstic <krsticm1996@gmail.com>
ADD target/search-service-0.0.1-SNAPSHOT.jar search-service.jar
ENTRYPOINT ["java", "-jar", "/search-service.jar"]
EXPOSE 8300