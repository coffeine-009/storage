/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 3:57 PM
 */

package com.thecoffeine.storage.models.services.implementations;

import com.thecoffeine.storage.models.services.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests of {@link FileServiceImpl}
 *
 * @version 1.0
 * @see FileServiceImpl
 */
@RunWith( SpringRunner.class )
public class FileServiceImplTests {

    @Mock
    private GridFsTemplate gridFsTemplate;

    @InjectMocks
    private FileService fileService = new FileServiceImpl();


    /**
     * Test delete by name.
     */
    @Test
    public void testDeleteByNameSuccess() {
        //- Mock -//
        doNothing().when( this.gridFsTemplate ).delete( any( Query.class ) );

        //- Perform -//
        this.fileService.delete( "testFileName" );

        //- Assertions -//
        verify( this.gridFsTemplate, times( 1 ) ).delete( any( Query.class ) );
    }
}
