package com.jobtracker.service;


import com.jobtracker.entity.TestJobEntity;
import com.jobtracker.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    public TestJobEntity create(String name) {
        TestJobEntity entity = TestJobEntity.builder()
                .name(name)
                .build();
        return repository.save(entity);
    }
}