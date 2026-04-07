package com.jobtracker.controller;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.service.ReminderService;
import com.jobtracker.util.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
@Table(name = "Reminder")
public class ReminderController {
    private final ReminderService reminderService;

    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Reminder created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReminderDto.class))
            ),
            @ApiResponse(
                    responseCode = "501",
                    description = "Internal server error",
                    content  =@Content(
                            mediaType = "application/json",
                            schema= @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    private ResponseEntity<ReminderDto> createReminder(
            @RequestBody ReminderRequest reminderRequest){
        return new ResponseEntity<>(reminderService.createReminder(reminderRequest), HttpStatus.CREATED);
    }
}
