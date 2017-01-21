package com.thecoffeine.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class.
 *
 * @version 1.0
 */
@SpringBootApplication
public class StorageApplication {

    /**
     * Entry point.
     *
     * @param args    Command line arguments.
     */
    public static void main( String[] args ) {
        SpringApplication.run( StorageApplication.class, args );
    }
}
