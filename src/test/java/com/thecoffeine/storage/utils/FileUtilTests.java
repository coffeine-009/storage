/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 3:48 PM
 */

package com.thecoffeine.storage.utils;

import org.junit.Test;

/**
 * Tests of {@link FileUtil}
 *
 * @version 1.0
 * @see FileUtil
 */
public class FileUtilTests {

    /**
     * Check if util class is abstract.
     */
    @Test( expected = InstantiationException.class)
    public void testNoInstances() throws IllegalAccessException, InstantiationException {
        FileUtil.class.newInstance();
    }
}
