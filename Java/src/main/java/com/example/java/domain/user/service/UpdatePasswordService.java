package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.exception.PasswordMissMatchException;
import com.example.java.domain.user.facade.UserFacade;
import com.example.java.domain.user.presentation.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UpdatePasswordRequest request) {
        User user = userFacade.getCurrentUser();
        if (passwordEncoder.matches(user.getPassword(), request.getNewPassword())) {
            throw new IllegalArgumentException("다른 비밀번호로 설정해주세요");
        }
        if (request.getNewPassword().equals(request.getNewPassword())) {
            throw PasswordMissMatchException.EXCEPTION;
        }

        user.passwordUpdate(passwordEncoder.encode(request.getNewPassword()));
    }
}
