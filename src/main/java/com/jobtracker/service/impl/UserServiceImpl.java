package com.jobtracker.service.impl;

import com.jobtracker.dto.RegisterRequest;
import com.jobtracker.entity.Job;
import com.jobtracker.entity.User;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.repository.UserRepository;
import com.jobtracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobRepository jobRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jobRepository=jobRepository;
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase(java.util.Locale.ROOT);

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        User user = new User();
        user.setEmail(email);

        // Never save the plain-text password.
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        user.setEnabled(true);

        userRepository.save(user);
    }
    private Job getJobOwnedByUser(Long jobId, String email) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));

        if (!job.getOwner().getEmail().equalsIgnoreCase(email)) {
            throw new AccessDeniedException("Access denied");
        }

        return job;
    }
}