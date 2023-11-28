package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.domain.repository.UserRepository;
import com.example.java.domain.user.presentation.dto.request.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUserService {
    private final UserRepository userRepository;

    @Transactional
    public void execute(UserSignupRequest request) {

        userRepository.save(
                User.builder()
                        .accountId(request.getAccountId())
                        .build()
        );
    }
}
