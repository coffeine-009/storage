#- Base image -#
FROM java:8

#- Author -#
MAINTAINER Vitaliy Tsutsman <vitaliy.tsutsman@musician-virtuoso.com>

#- Create app dir -#
RUN mkdir /opt/storage

#- Copy sources -#
COPY . /opt/storage

# Define dafault value of DB host
ENV DB_HOST localhost

#- Set up work dir -#
WORKDIR /opt/storage

#- Build app -#
RUN ./gradlew clean build

#- Expose port -#
EXPOSE 8080

#- Run app -#
CMD [ "./gradlew", "bootRun" ]
