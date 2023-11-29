package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.domain.repository.UserRepository;
import com.example.java.domain.user.exception.AccountExistsException;
import com.example.java.domain.user.facade.UserFacade;
import com.example.java.domain.user.presentation.dto.request.UpdateMyInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public void execute(UpdateMyInfoRequest request) {
        User user = userFacade.getCurrentUser();
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw AccountExistsException.EXCEPTION;
        }
        user.update(request.getAccountId(), request.getName(), request.getSex());
    }
}
