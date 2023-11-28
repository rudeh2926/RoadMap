package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.domain.repository.UserRepository;
import com.example.java.domain.user.exception.AccountExistsException;
import com.example.java.domain.user.presentation.dto.request.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UserSignupRequest request) {

        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw AccountExistsException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .accountId(request.getAccountId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .sex(request.getSex())
                        .build()
        );
    }
}
