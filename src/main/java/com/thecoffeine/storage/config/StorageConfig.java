/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 10:01 PM
 */

package com.thecoffeine.storage.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Storage configuration.
 *
 * @version 1.0
 */
@Profile( "default" )
@Configuration
public class StorageConfig extends AbstractMongoConfiguration {

    /**
     * Host of storage.
     */
    private final String host;

    /**
     * Storage port.
     */
    private final int port;

    /**
     * Database name.
     */
    private final String database;

    /**
     * Username.
     */
    private final String username;

    /**
     * Password.
     */
    private final char[] password;


    /**
     * Constructor to create storage config.
     *
     * @param host        Storage host.
     * @param port        Storage port.
     * @param database    Storage database name.
     * @param username    Username to access.
     * @param password    Password to access.
     */
    public StorageConfig(
        @Value( "${spring.data.mongodb.host}" )
        String host,

        @Value( "${spring.data.mongodb.port}" )
        int port,

        @Value( "${spring.data.mongodb.database}" )
        String database,

        @Value( "${spring.data.mongodb.username}" )
        String username,

        @Value( "${spring.data.mongodb.password}" )
        char[] password
    ) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password.clone();
    }

    /**
     * GridFs template for working with mongodb's GridFs storage.
     *
     * @return GridFsTemplate.
     *
     * @throws Exception    Cannot create {@link GridFsTemplate}
     */
    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(
            this.mongoDbFactory(),
            this.mappingMongoConverter()
        );
    }

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    /**
     * Return the {@link Mongo} instance to connect to.
     * Annotate with {@link Bean} in case you want to expose a
     * {@link Mongo} instance to the {@link ApplicationContext}.
     *
     * @return Mongo client.
     */
    @Override
    public Mongo mongo() {
        return new MongoClient(
            new ServerAddress(
                this.host,
                this.port
            ),
            Stream.of(
                MongoCredential.createCredential(
                    this.username,
                    this.getDatabaseName(),
                    this.password
                )
            ).collect( Collectors.toList() )
        );
    }
}
