/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 2/16/17 12:34 AM
 */

package com.thecoffeine.storage.utils;


import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mock user.
 * @link http://stackoverflow.com/questions/31641562/spring-oauth-and-boot-integration-test/31679649#31679649
 *
 * @version 1.0
 */
@Retention( RetentionPolicy.RUNTIME )
@WithSecurityContext( factory = WithMockSecurityUserContextFactory.class )
public @interface WithMockSecurityUser {

    String username() default "test@unit.org";

    String password() default "password";
}
