FROM openjdk:15
VOLUME /tmp
EXPOSE 8080
ADD ./target/technicaltest-0.0.1-SNAPSHOT.jar mutants-server.jar
ENTRYPOINT ["java","-jar","/mutants-server.jar"]
