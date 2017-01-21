/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/10/17 10:56 PM
 */

package com.thecoffeine.storage.controllers;

import com.thecoffeine.storage.models.mocks.FileMock;
import com.thecoffeine.storage.models.services.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link FileController}.
 * @see FileController
 *
 * @version 1.0
 */
@RunWith( SpringRunner.class )
@WebMvcTest( FileController.class )
public class FileControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;


    /**
     * Test of getting list of files.
     *
     * @throws Exception
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mocks -//
        when( this.fileService.findAll() ).thenReturn( FileMock.getList() );

        //- Performing -//
        this.mockMvc.perform(
            get( "/files" )
                .accept( MediaType.parseMediaType( "application/json;charset=UTF-8" ) )
                .locale( Locale.ENGLISH )
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType( "application/json;charset=UTF-8" ) );
    }

    /**
     * Test of getting list of files for unacceptable content type..
     *
     * @throws Exception
     */
    @Test
    public void testListActionFailure() throws Exception {
        //- Mocks -//
        when( this.fileService.findAll() ).thenReturn( FileMock.getList() );

        //- Performing -//
        this.mockMvc.perform(
            get( "/files" )
                .accept( MediaType.parseMediaType("application/xml;charset=UTF-8"))
        )
        .andExpect( status().isNotAcceptable() );
    }

    /**
     * Test of creating(uploading) file.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock data -//
        MockMultipartFile mockFile = new MockMultipartFile(
            "file",
            "Merry_Christmas.xml",
            "application/xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes( Charset.forName( "UTF-8" ) )
        );
        //- Mocks -//
        when( this.fileService.create( any( MultipartFile.class ) ) ).thenReturn( FileMock.getFile() );
        //- Performing -//
        this.mockMvc.perform(
            fileUpload( "/files" )
                .file(mockFile)
                .accept( MediaType.parseMediaType( "application/json;charset=UTF-8" ) )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test of getting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Mocks -//
        when( this.fileService.find( anyString() ) ).thenReturn( FileMock.getFile() );

        //- Performing -//
        this.mockMvc.perform(
            get( "/files/MozartPianoSonata.xml" )
                .accept( MediaType.parseMediaType("application/xml"))
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of getting file by name.
     * File does not exist.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Mocks -//
        when( this.fileService.find( anyString() ) ).thenReturn( null );

        //- Performing -//
        this.mockMvc.perform(
            get( "/files/MozartSonata.xml" )
                .accept( MediaType.parseMediaType("application/xml"))
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test of deleting file by name.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mocks -//
        doNothing().when( this.fileService ).delete( anyString() );

        //- Performing -//
        this.mockMvc.perform(
            delete( "/files/Mozart.xml" )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of deleting file by name.
     * File does not exist.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mocks -//
        doThrow( EmptyResultDataAccessException.class ).when( this.fileService ).delete( anyString() );

        //- Performing -//
        this.mockMvc.perform(
            delete( "/files/MozartSong.xml" )
        )
            .andExpect( status().isOk() );
    }
}
