/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 10:47 PM
 */

package com.thecoffeine.storage.controllers;

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
import java.util.List;
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


    /**
     * Get list of files per page.
     *
     * @return List of files.
     */
    @RequestMapping( method = RequestMethod.GET )
    public List<File> listAction() {
        return this.fileService.findAll();
    }

    /**
     * Create a new file(Upload).
     *
     * @param file        File data.
     * @param response    Http response.
     *
     * @return Created file.
     */
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
            return this.fileService.create( file );
        } catch ( IOException e ) {
            //- Error: cannot read input file -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Get file by name.
     *
     * @param name        File's name.
     * @param response    HTTP response.
     *
     * @return Original file.
     */
    @RequestMapping( path = "/{name:.+}", method = RequestMethod.GET )
    @ResponseBody
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

            return file.getContent();
        } catch (
            IllegalArgumentException
            | NullPointerException
            | IOException e
        ) {
            //- Error: cannot find file or content -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Delete file by name.
     *
     * @param name        File's name.
     * @param response    HTTP response.
     */
    @RequestMapping( path = "/{name:[\\w\\.\\_\\-]+}", method = RequestMethod.DELETE )
    public void deleteAction(
        @PathVariable( "name" )
        String name,

        HttpServletResponse response
    ) {
        try {
            //- Delete file -//
            this.fileService.delete( name );
        } catch ( Exception e ) {
            //- Error: cannot delete this file -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }

    /**
     * Delete file by id.
     *
     * @param id        File's name.
     * @param response    HTTP response.
     */
    @RequestMapping( path = "/{id:\\w+}", method = RequestMethod.DELETE )
    public void deleteAction(
        @PathVariable( "id" )
        Object id,

        HttpServletResponse response
    ) {
        try {
            //- Delete file -//
            this.fileService.delete( id );
        } catch ( Exception e ) {
            //- Error: cannot delete this file -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
