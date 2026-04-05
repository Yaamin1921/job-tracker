package com.jobtracker.service;


import com.jobtracker.entity.TestEntity;
import com.jobtracker.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    public TestEntity create(String name) {
        TestEntity entity = TestEntity.builder()
                .name(name)
                .build();
        return repository.save(entity);
    }
}