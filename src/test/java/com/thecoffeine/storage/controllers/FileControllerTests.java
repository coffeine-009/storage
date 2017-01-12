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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.mockito.Mockito.when;
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


    @Test
    public void testListActionSuccess() throws Exception {
        //- Mocks -//
        when( this.fileService.findAll() ).thenReturn( FileMock.getList() );

        //- Performing -//
        this.mockMvc.perform(
            get( "/files" )
                .accept( MediaType.parseMediaType("application/json;charset=UTF-8"))
                .locale( Locale.ENGLISH )
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType("application/json;charset=UTF-8") );
    }

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
}
