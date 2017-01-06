/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 10:47 PM
 */

package com.thecoffeine.storage.controllers;

import com.mongodb.gridfs.GridFSFile;
import com.thecoffeine.storage.models.entities.File;
import com.thecoffeine.storage.models.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for files.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/files" )
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping( method = RequestMethod.GET )
    public List<File> listAction() {
        return this.fileService.findAll().stream()
            .map((gridFSFile) -> new File(
                "" + gridFSFile.getId(),
                gridFSFile.getFilename(),
                gridFSFile.getContentType(),
                gridFSFile.getLength(),
                gridFSFile.getChunkSize(),
                OffsetDateTime.ofInstant( gridFSFile.getUploadDate().toInstant(), ZoneId.systemDefault() ),
                gridFSFile.getMD5()
            ))
            .collect( Collectors.toList()    );
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public File createAction(
        @RequestParam( "file" )
        MultipartFile file,

        HttpServletResponse response
    ) {
        try {
            //- Set successful HTTP status -//
            response.setStatus( HttpServletResponse.SC_CREATED );
            //- Store file -//
            final GridFSFile gridFSFile = this.fileService.create( file );
            return new File(
                "" + gridFSFile.getId(),
                gridFSFile.getFilename(),
                gridFSFile.getContentType(),
                gridFSFile.getLength(),
                gridFSFile.getChunkSize(),
                OffsetDateTime.ofInstant( gridFSFile.getUploadDate().toInstant(), ZoneId.systemDefault() ),
                gridFSFile.getMD5()
            );
        } catch ( IOException e ) {
            //- Error: cannot read input file -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    @RequestMapping( path = "/{name:.+}", method = RequestMethod.GET )
    public byte[] readAction(
        @PathVariable( "name" )
        String name,

        HttpServletResponse response
    ) {
        try {
            //- Search of file -//
            final File file = this.fileService.find( name );

            //- Check file -//
            notNull( file );

            //- Prepare response -//
            response.addHeader( HttpHeaders.CONTENT_TYPE, file.getContentType() );
        } catch ( IllegalArgumentException e ) {
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( IOException e ) {

        }

        return null;
    }
}
