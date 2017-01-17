FROM mongo:3.4

COPY ./keyfile /tmp/keyfile

RUN chmod 400 /tmp/keyfile
RUN chown mongodb:mongodb /tmp/keyfile

CMD [ "mongod" ]
