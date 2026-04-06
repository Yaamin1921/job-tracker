package com.jobtracker.acceptance;


import com.jobtracker.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class JobControllerTest {
    private static final String GET_JOB_END_POINT="/api/jobs";

    @Autowired
    private MockMvc mockMvc;
/*
    @Test
    void shouldGetAllJobs() throws Exception {

        MockHttpServletRequestBuilder mockMvcRequestBuilders= get(GET_JOB_END_POINT)
                        .accept(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(mockMvcRequestBuilders).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(result, TestUtils.getFileContent(""));

    }*/

}
