FROM anapsix/alpine-java
MAINTAINER Milos Krstic <krsticm1996@gmail.com>
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]
EXPOSE 8762
