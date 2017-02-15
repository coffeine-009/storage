/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 2/15/17 9:20 AM
 */

package com.thecoffeine.storage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Security configuration.
 *
 * @version 1.0
 */
@Profile( "default" )
@Configuration
@EnableResourceServer
public class SecurityConfig {

}
