/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/10/17 11:10 PM
 */

package com.thecoffeine.storage.models.mocks;

import com.thecoffeine.storage.models.entities.File;

import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File mocks.
 *
 * @version 1.0
 */
public abstract class FileMock {

    /**
     * Get list of mocked files.
     *
     * @return List of files.
     */
    public static List<File> getList() {
        return Stream.of( getFile() )
            .collect( Collectors.toList() );
    }

    /**
     * Get mocked file.
     *
     * @return File.
     */
    public static File getFile() {
        //- Create mock of file -//
        final File file = new File(
            "id",
            "test.xml",
            "application/xml",
            1024L,
            8L,
            OffsetDateTime.now(),
            "ert435tertertr354"
        );

        //- Set content -//
        file.setContent(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes(
                Charset.forName( "UTF-8" )
            )
        );

        return file;
    }
}
