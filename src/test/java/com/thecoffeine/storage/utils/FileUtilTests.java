/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 3:48 PM
 */

package com.thecoffeine.storage.utils;

import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

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
    @Test
    public void testNoInstances() {
        //- Trick for cobertura -//
        new FileUtil() {};//FIXME: remove if cobertura can handle this.

        //- Real check -//
        assertTrue(
            "Util class has to be abstract",
            Modifier.isAbstract( FileUtil.class.getModifiers() )
        );
    }
}
