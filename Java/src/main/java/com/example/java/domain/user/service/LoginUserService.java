package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.exception.PasswordMissMatchException;
import com.example.java.domain.user.facade.UserFacade;
import com.example.java.domain.user.presentation.dto.request.UserLoginRequest;
import com.example.java.global.security.jwt.JwtTokenProvider;
import com.example.java.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public TokenResponse execute(UserLoginRequest request) {
        User user = userFacade.getUserByAccountId(request.getAccountId());

        if (passwordEncoder.matches(user.getPassword(), request.getPassword())) {
            throw PasswordMissMatchException.EXCEPTION;
        }

        return jwtTokenProvider.getToken(user.getAccountId());
    }
}
