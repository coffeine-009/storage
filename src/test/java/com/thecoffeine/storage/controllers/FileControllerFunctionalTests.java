/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/12/17 10:59 PM
 */

package com.thecoffeine.storage.controllers;

import com.thecoffeine.storage.AbstractTests;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Functional tests for {@link FileController}.
 * @see FileController
 *
 * @version 1.0
 */
public class FileControllerFunctionalTests extends AbstractTests {

    @Test
    public void testListActionSuccess() throws Exception {
        //- Performing -//
        this.mockMvc.perform(
            get( "/files" )
                .accept( MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType("application/json;charset=UTF-8") )
            .andDo( document( "list-files" ) )
            .andDo( print() );
    }
}
