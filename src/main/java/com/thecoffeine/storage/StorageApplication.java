/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 4:22 PM
 */

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
