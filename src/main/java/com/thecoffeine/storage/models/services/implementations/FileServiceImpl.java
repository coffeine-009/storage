/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 11:08 PM
 */

package com.thecoffeine.storage.models.services.implementations;

import com.mongodb.gridfs.GridFSDBFile;
import com.thecoffeine.storage.models.entities.File;
import com.thecoffeine.storage.models.services.FileService;
import com.thecoffeine.storage.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public List<File> findAll() {
        return this.gridFsTemplate.find( null )
            .stream()
            .map( FileUtil::gridFsDbFileToFile )
            .collect( Collectors.toList() );
    }

    /**
     * Create a new File(Upload).
     *
     * @return Created file.
     */
    @Override
    public File create( MultipartFile file ) throws IOException {
        //- Store file -//
        return FileUtil.gridFsDbFileToFile(
            this.gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType()
            )
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
        //- Find file -//
        final GridFSDBFile gridFsDbFile = this.gridFsTemplate.findOne(
            Query.query(
                GridFsCriteria.whereFilename().is( fileName )
            )
        );

        //- Convert to File -//
        final File file = FileUtil.gridFsDbFileToFile( gridFsDbFile );

        //- Create buffer -//
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //- Write content into buffer -//
        gridFsDbFile.writeTo( os );

        //- Set content into file -//
        file.setContent( os.toByteArray() );

        return file;
    }

    /**
     * Delete file by name.
     *
     * @param name File's name.
     */
    @Override
    public void delete( String name ) {
        this.gridFsTemplate.delete(
            Query.query(
                GridFsCriteria.whereFilename().is( name )
            )
        );
    }

    /**
     * Delete file by id.
     *
     * @param id File's id.
     */
    @Override
    public void delete( Object id ) {
        this.gridFsTemplate.delete(
            new Query(
                Criteria.where( "_id" ).is( id )
            )
        );
    }
}
