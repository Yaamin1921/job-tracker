package com.jobtracker.controller;

import com.jobtracker.dto.ActivityDto;
import com.jobtracker.dto.JobDto;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.service.JobService;
import com.jobtracker.util.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

    private final JobService jobService;

    // CREATE
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Post created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "applicat/json",
                            schema = @Schema(implementation = ErrorResponse.class ))
            )
    })
    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
        return new ResponseEntity<>(jobService.createJob(jobDto), HttpStatus.CREATED);
    }

    // GET ALL
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successfully get jobs",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "applicat/json",
                            schema = @Schema(implementation = ErrorResponse.class ))
            )
    })
    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // GET BY ID
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successfully get jobs with id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "applicat/json",
                            schema = @Schema(implementation = ErrorResponse.class ))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    // UPDATE STATUS

    @PutMapping("/{id}/status")
    public ResponseEntity<JobDto> updateStatus(
            @PathVariable Long id,
            JobStatus request) {

        return ResponseEntity.ok(jobService.updateJobStatus(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }

    //get timeline
    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<ActivityDto>> getTimeline(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getTimeline(id));
    }
}