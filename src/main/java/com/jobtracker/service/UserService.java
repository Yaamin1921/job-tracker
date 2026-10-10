package com.jobtracker.service;

import com.jobtracker.dto.RegisterRequest;

public interface UserService {

    void register(RegisterRequest request);
}