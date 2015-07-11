FROM java:8
WORKDIR /usr/src
COPY ./target/Authorisation-0.1.0.jar /usr/src/
EXPOSE 9001
ENTRYPOINT ["java"]
CMD ["-jar", "Authorisation-0.1.0.jar"]