/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/21/17 4:23 PM
 */

package com.thecoffeine.storage;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles( "tests" )
@AutoConfigureRestDocs( "build/generated-snippets" )
@AutoConfigureMockMvc
@RunWith( SpringRunner.class )
@SpringBootTest
public abstract class AbstractTests {

    @Autowired
    protected MockMvc mockMvc;
}
