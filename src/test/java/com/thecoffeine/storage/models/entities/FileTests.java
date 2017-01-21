/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 4:31 PM
 */

package com.thecoffeine.storage.models.entities;

import org.junit.Test;

import java.time.OffsetDateTime;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests of {@link File}
 *
 * @version 1.0
 * @see File
 */
public class FileTests {

    /**
     * Test setters of file.
     */
    @Test
    public void testSetters() {
        //- Assumptions -//
        final OffsetDateTime now = OffsetDateTime.now();
        File file = new File();

        //- Set data -//
        file.setId( "testId" );
        file.setName( "testFile" );
        file.setContentType( "application/xml" );
        file.setLength( 1024 );
        file.setChunkSize( 256 );
        file.setUploadDate( now );
        file.setMd5( "MD5 hash" );

        //- Assertions -//
        assertEquals( "testId", file.getId() );
        assertEquals( "testFile", file.getName() );
        assertEquals( "application/xml", file.getContentType() );
        assertEquals( 1024, file.getLength() );
        assertEquals( 256, file.getChunkSize() );
        assertEquals( now, file.getUploadDate() );
        assertEquals( "MD5 hash", file.getMd5() );
    }
}
