FROM openjdk:17-alpine
COPY ./target/readandreturn-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java","-jar","readandreturn-0.0.1-SNAPSHOT.jar"]
