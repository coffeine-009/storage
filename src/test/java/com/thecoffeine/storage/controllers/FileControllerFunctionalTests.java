/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/12/17 10:59 PM
 */

package com.thecoffeine.storage.controllers;

import com.thecoffeine.storage.AbstractTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Functional tests for {@link FileController}.
 * @see FileController
 *
 * @version 1.0
 */
public class FileControllerFunctionalTests extends AbstractTests {

    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Before
    public void setUp() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream( "src/test/java/resources/MozartPianoSonata.xml" );
        this.gridFsTemplate.store(
            inputStream,
            "MozartPianoSonata.xml",
            "application/xml"
        );

        InputStream is = new FileInputStream( "src/test/java/resources/MozartPianoSonata.xml" );
        this.gridFsTemplate.store(
            is,
            "Mozart.xml",
            "application/xml"
        );
    }

    /**
     * Test of getting list of files.
     *
     * @throws Exception
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files" )
                .accept( MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType("application/json;charset=UTF-8") )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( greaterThan( 1 ) ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].name", notNullValue() ) )
            .andExpect( jsonPath( "$[:2].name", containsInAnyOrder( "MozartPianoSonata.xml", "Mozart.xml" ) ) )
            .andExpect( jsonPath( "$[*].contentType", notNullValue() ) )
            .andExpect( jsonPath( "$[:1].contentType", containsInAnyOrder( "application/xml" ) ) )
            .andExpect( jsonPath( "$[*].length", notNullValue() ) )
            .andExpect( jsonPath( "$[*].chunkSize", notNullValue() ) )
            .andExpect( jsonPath( "$[*].uploadDate", notNullValue() ) )
            .andExpect( jsonPath( "$[*].md5", notNullValue() ) )
            .andDo( document( "list-files" ) );
    }

    /**
     * Test of getting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files/MozartPianoSonata.xml" )
                .accept( MediaType.parseMediaType("application/xml"))
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType("application/xml") )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andDo( document( "file-success" ) );
    }

    /**
     * Test of getting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files/MozartSonata.xml" )
                .accept( MediaType.parseMediaType("application/xml"))
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "file-failure-not-found" ) );
    }

    /**
     * Test of creating(upload) file.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock data -//
        MockMultipartFile file = new MockMultipartFile( "file", "Jingle-Bells.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes() );
        //- Performing -//
        this.mockMvc.perform(
            fileUpload( "/files" )
                .file(file)
                .accept( MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
            .andExpect( status().isCreated() )
            .andExpect( content().contentType("application/json;charset=UTF-8") )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$.id", notNullValue() ) )
            .andExpect( jsonPath( "$.name", notNullValue() ) )
            .andExpect( jsonPath( "$.name" ).value( "Jingle-Bells.xml" ) )
            .andExpect( jsonPath( "$.contentType", notNullValue() ) )
            .andExpect( jsonPath( "$.contentType" ).value( "application/xml" ) )
            .andExpect( jsonPath( "$.length", notNullValue() ) )
            .andExpect( jsonPath( "$.chunkSize", notNullValue() ) )
            .andExpect( jsonPath( "$.uploadDate", notNullValue() ) )
            .andExpect( jsonPath( "$.md5", notNullValue() ) )
            .andDo( document( "file-create-success" ) );
    }

    /**
     * Test of deleting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files/Mozart.xml" )
        )
            .andExpect( status().isOk() )
            .andDo( document( "file-delete-success" ) );
    }

    /**
     * Test of deleting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files/MozartSong.xml" )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "file-delete-failure" ) );
    }
}
