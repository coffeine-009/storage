/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 2/11/17 11:52 PM
 */

package com.thecoffeine.storage.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Discovery client configuration.
 *
 * @version 1.0
 */
@Profile( "default" )
@Configuration
@EnableDiscoveryClient
public class DiscoveryClientConfig {

}
