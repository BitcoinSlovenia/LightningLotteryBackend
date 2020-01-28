FROM openjdk:8-jdk-alpine

# https://security.stackexchange.com/questions/106860/can-a-root-user-inside-a-docker-lxc-break-the-security-of-the-whole-system
# https://spring.io/guides/gs/spring-boot-docker/
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","LightningLottery.class"]

# TODO Build a Docker Image with Maven https://spring.io/guides/gs/spring-boot-docker/ 
