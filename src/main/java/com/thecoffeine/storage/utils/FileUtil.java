/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/9/17 4:36 PM
 */

package com.thecoffeine.storage.utils;

import com.mongodb.gridfs.GridFSFile;
import com.thecoffeine.storage.models.entities.File;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Util for working with files.
 *
 * @version 1.0
 */
public abstract class FileUtil {

    /**
     * Converts GridFSFile to File.
     *
     * @param file    GridFSFile.
     *
     * @return File.
     */
    public static File gridFsDbFileToFile( GridFSFile file ) {
        return  new File(
            "" + file.getId(),
            file.getFilename(),
            file.getContentType(),
            file.getLength(),
            file.getChunkSize(),
            OffsetDateTime.ofInstant( file.getUploadDate().toInstant(), ZoneId.systemDefault() ),
            file.getMD5()
        );
    }
}
