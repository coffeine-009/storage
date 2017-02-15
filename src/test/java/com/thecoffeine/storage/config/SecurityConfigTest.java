/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 2/15/17 11:02 PM
 */

package com.thecoffeine.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by vitaliy on 2/15/17.
 */
@Profile( "tests" )
@Configuration
public class SecurityConfigTest {

    @Bean
    @Primary
    public Boolean oauth2StatelessSecurityContext() {
        return Boolean.FALSE;
    }
}
