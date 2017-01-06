/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 11:08 PM
 */

package com.thecoffeine.storage.models.services.implementations;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.thecoffeine.storage.models.entities.File;
import com.thecoffeine.storage.models.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of FileService.
 * @see FileService
 *
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;


    /**
     * Find all files.
     *
     * @return List of files.
     */
    @Override
    public List<GridFSDBFile> findAll() {
        return this.gridFsTemplate.find( null )
            .stream()
            .collect( Collectors.toList() );
    }

    /**
     * Create a new File.
     *
     * @return Created file.
     */
    @Override
    public GridFSFile create( MultipartFile file ) throws IOException {
        return this.gridFsTemplate.store(
            file.getInputStream(),
            file.getOriginalFilename(),
            file.getContentType()
        );
    }

    /**
     * Find file by name.
     *
     * @param fileName Name of file.
     *
     * @return File.
     */
    @Override
    public File find( String fileName ) throws IOException {
        final GridFSDBFile file = this.gridFsTemplate.findOne(
            Query.query( GridFsCriteria.whereFilename().is( fileName ) )
        );

        final File f = new File(
            "" + file.getId(),
            file.getFilename(),
            file.getContentType(),
            file.getLength(),
            file.getChunkSize(),
            OffsetDateTime.ofInstant( file.getUploadDate().toInstant(), ZoneId.systemDefault() ),
            file.getMD5()
        );

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        file.writeTo( os );
        f.setContent( os.toByteArray() );//TODO: code stype


        return f;
    }
}
