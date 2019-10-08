FROM java:8

MAINTAINER Anthonyzero <736252868@qq.com>

RUN mkdir /app

WORKDIR /app

COPY xbook-mall-service-redis.jar /app

RUN mkdir logs

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/xbook-mall-service-redis.jar", "--spring.profiles.active=pro", ">logs/app.log 2>&1 &"]

EXPOSE 20881