FROM java:8
WORKDIR /usr/src
COPY ./target/Authorisation-0.1.0.jar /usr/src/
ENTRYPOINT ["java"]
CMD ["-jar", "permissions-0.1.0.jar"]