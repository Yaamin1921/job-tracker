package com.jobtracker.controller;

import com.jobtracker.dto.JobDto;
import com.jobtracker.dto.UpdateJobStatusRequest;
import com.jobtracker.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // CREATE
    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
        return new ResponseEntity<>(jobService.createJob(jobDto), HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<JobDto> updateStatus(
            @PathVariable Long id,
             @RequestBody UpdateJobStatusRequest request) {

        return ResponseEntity.ok(jobService.updateJobStatus(id, request.getStatus()));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}