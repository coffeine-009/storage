/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 9:42 PM
 */

package com.thecoffeine.storage.models.entities;

import java.time.OffsetDateTime;

/**
 * Entity for working with files.
 *
 * @version 1.0
 */
public class File {

    private String id;

    private String name;

    private String contentType;

    private long length;
    private long chunkSize;
    private OffsetDateTime uploadDate;
    private String md5;

    private byte[] content;

    public File() {
    }

    public File(
        String id,
        String name,
        String contentType,
        long length,
        long chunkSize,
        OffsetDateTime uploadDate,
        String md5
    ) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.length = length;
        this.chunkSize = chunkSize;
        this.uploadDate = uploadDate;
        this.md5 = md5;
    }
    //- SECTION :: GET -//
    /**
     * Get id of file.
     *
     * @return Id of file.
     */
    public String getId() {
        return id;
    }

    /**
     * Get file name.
     *
     * @return name of file.
     */
    public String getName() {
        return name;
    }

    /**
     * Get mime type of file.
     *
     * @return Valid mime type.
     */
    public String getContentType() {
        return contentType;
    }

    public long getLength() {
        return length;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public OffsetDateTime getUploadDate() {
        return uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public byte[] getContent() {
        return content;
    }
    //- SECTION :: SET -//
    /**
     * Set id of file.
     * Note: use in tests or libs.
     *
     * @param id    File id.
     */
    public void setId( String id ) {
        this.id = id;
    }

    /**
     * Set file name.
     *
     * @param name    Name of file.
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Set a valid mime type.
     *
     * @param contentType    Mime type.
     */
    public void setContentType( String contentType ) {
        this.contentType = contentType;
    }

    public void setLength( long length ) {
        this.length = length;
    }

    public void setChunkSize( long chunkSize ) {
        this.chunkSize = chunkSize;
    }

    public void setUploadDate( OffsetDateTime uploadDate ) {
        this.uploadDate = uploadDate;
    }

    public void setMd5( String md5 ) {
        this.md5 = md5;
    }

    public void setContent( byte[] content ) {
        this.content = content;
    }
}
