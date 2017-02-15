/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/10/17 11:01 PM
 */

package com.thecoffeine.storage.controllers;

import com.thecoffeine.storage.AbstractTests;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/**
 * Abstract class for controller's tests.
 *
 * @version 1.0
 */
public abstract class AbstractRestControllerTests extends AbstractTests {

    public static RequestPostProcessor security() {
        return SecurityMockMvcRequestPostProcessors.securityContext(
            SecurityContextHolder.getContext()
        );
    }

}
