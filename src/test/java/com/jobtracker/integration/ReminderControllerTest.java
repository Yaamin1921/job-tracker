package com.jobtracker.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.ReminderType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.OffsetDateTime;

import static com.jobtracker.TestUtils.assertThatJsonResponseEqualsToExpected;
import static com.jobtracker.TestUtils.getFileContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@Profile("test")
@AutoConfigureMockMvc
public class ReminderControllerTest {
    private static final String API_V1_CREATE_REMINDERS="/api/jobs";
    @Autowired
    private MockMvc mockMvc;


    void crateReminders()throws Exception
    {
        ObjectMapper objectMapper=new ObjectMapper();
        ReminderRequest request=ReminderRequest.builder()
                .jobId(Long.valueOf(2))
                .email("yaamin1921413@gmail.com")
                .reminderTime(OffsetDateTime.now().plusMinutes(3))
                .type(ReminderType.FOLLOW_UP)
                .build();

        MockHttpServletRequestBuilder requestBuilder=post(API_V1_CREATE_REMINDERS)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        ResultActions resultActions= mockMvc.perform(requestBuilder);

        assertThatJsonResponseEqualsToExpected(resultActions,getFileContent(""));
    }

}
