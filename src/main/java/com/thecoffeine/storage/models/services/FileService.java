/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 11:05 PM
 */

package com.thecoffeine.storage.models.services;

import com.thecoffeine.storage.models.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Interface of file service.
 *
 * @version 1.0
 */
public interface FileService {

    /**
     * Find all files.
     *
     * @return List of files.
     */
    List<File> findAll();

    /**
     * Create a new File.
     *
     * @return Created file.
     *
     * @throws IOException if cannot read input file.
     */
    File create( MultipartFile file ) throws IOException;

    /**
     * Find file by name.
     *
     * @param fileName    Name of file.
     *
     * @return File.
     */
    File find( String fileName ) throws IOException;

    /**
     * Delete file by name.
     *
     * @param name    File's name.
     */
    void delete( String name );

    /**
     * Delete file by id.
     *
     * @param id    File's id.
     */
    void delete( Object id );
}
