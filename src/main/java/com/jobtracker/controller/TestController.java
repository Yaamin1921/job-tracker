package com.jobtracker.controller;

import com.jobtracker.entity.TestEntity;
import com.jobtracker.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @PostMapping
    public TestEntity create(@RequestParam String name) {
        return service.create(name);
    }
}