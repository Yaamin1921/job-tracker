package com.jobtracker.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderType;
import com.jobtracker.service.ReminderService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.jobtracker.TestUtils.assertThatJsonResponseEqualsToExpected;
import static com.jobtracker.TestUtils.getFileContent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ReminderControllerTest {
    private static final String API_V1_CREATE_REMINDERS="/api/reminders";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ReminderService reminderService;

   @Test
  // @Sql("classpath:database-init-scripts/reminder/reminder-data.sql")
    void crateReminders()throws Exception
    {
        ReminderRequest request=ReminderRequest.builder()
                .jobId(Long.valueOf(999))
                .email("yaamin1921413@gmail.com")
                .reminderTime(OffsetDateTime.parse("2026-04-12T11:48:26.5739063Z"))
                .type(ReminderType.FOLLOW_UP)
                .build();

        MockHttpServletRequestBuilder requestBuilder=post(API_V1_CREATE_REMINDERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions= mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        assertThatJsonResponseEqualsToExpected(resultActions,getFileContent("data/create-reminders.json"));
        Optional<Reminder> reminderOpt =
                reminderService.getDueReminderByJobId(request.getJobId());

        assertThat(reminderOpt).isPresent();

        Reminder reminder = reminderOpt.get();

        assertThat(reminder)
                .extracting(Reminder::getJobId,
                        Reminder::getEmail,
                        Reminder::getType)
                .containsExactly(999L, "yaamin1921413@gmail.com", ReminderType.FOLLOW_UP);
    }

}
