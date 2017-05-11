#- Base image -#
FROM java:8

#- Author -#
MAINTAINER Vitaliy Tsutsman <vitaliy.tsutsman@musician-virtuoso.com>

#- Create app dir -#
RUN mkdir /opt/storage

# Define dafault value of DB host
ENV DB_HOST localhost

#- Set up work dir -#
WORKDIR /opt/storage

#- Expose port -#
EXPOSE 8080

#- Run app -#
CMD [ "java", "-jar", "/opt/storage/storage.jar" ]

#- Add app jar -#
ADD ./build/libs/storage-*.jar /opt/storage/storage.jar
