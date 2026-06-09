package com.jobtracker.controller;

import com.jobtracker.dto.DashboardResponse;
import com.jobtracker.service.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name="Dashboard")
@AllArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getStats() {
        return ResponseEntity.ok(
                dashboardService.getDashboardStats()
        );
    }
}
