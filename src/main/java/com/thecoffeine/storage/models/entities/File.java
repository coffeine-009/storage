/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 9:42 PM
 */

package com.thecoffeine.storage.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Entity for working with files.
 * Note: Actually it is TO object between system and storage.
 *
 * @version 1.0
 */
public class File implements Serializable {

    /**
     * Id of file.
     */
    private String id;

    /**
     * File's name.
     */
    private String name;

    /**
     * Content type. Valid mime-type.
     */
    private String contentType;

    /**
     * Length of file.
     */
    private long length;

    /**
     * File's chunk size.
     */
    private long chunkSize;

    /**
     * Date and time of upload.
     */
    private OffsetDateTime uploadDate;

    /**
     * MD5 hash of file.
     */
    private String md5;

    /**
     * File's content.
     */
    @JsonIgnore
    private byte[] content;


    /**
     * Default constructor.
     */
    public File() {
        //- Default initialization -//
    }

    /**
     * Create a new File.
     *
     * @param id             Id of file.
     * @param name           Name of file.
     * @param contentType    Media type.
     * @param length         Length of content.
     * @param chunkSize      Size of chunk.
     * @param uploadDate     Date and time of upload.
     * @param md5            MD5 hash of file.
     */
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

    /**
     * Gets length.
     *
     * @return Length in bytes.
     */
    public long getLength() {
        return length;
    }

    /**
     * Gets chunk's size.
     *
     * @return Size of chunk.
     */
    public long getChunkSize() {
        return chunkSize;
    }

    /**
     * Date and time of upload.
     *
     * @return Upload date and time.
     */
    public OffsetDateTime getUploadDate() {
        return uploadDate;
    }

    /**
     * Gets MD5 hash.
     *
     * @return MD5 hash of file.
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Gets content.
     *
     * @return File's content.
     */
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

    /**
     * Sets length of file.
     *
     * @param length    Count bytes.
     */
    public void setLength( long length ) {
        this.length = length;
    }

    /**
     * Sets size of chunk.
     *
     * @param chunkSize    Size of chunk.
     */
    public void setChunkSize( long chunkSize ) {
        this.chunkSize = chunkSize;
    }

    /**
     * Date and time of upload.
     *
     * @param uploadDate    Date and time.
     */
    public void setUploadDate( OffsetDateTime uploadDate ) {
        this.uploadDate = uploadDate;
    }

    /**
     * Sets MD5 hash.
     *
     * @param md5    MD5 hash.
     */
    public void setMd5( String md5 ) {
        this.md5 = md5;
    }

    /**
     * Sets content.
     *
     * @param content    File's bytes.
     */
    public void setContent( byte[] content ) {
        this.content = content;
    }
}
