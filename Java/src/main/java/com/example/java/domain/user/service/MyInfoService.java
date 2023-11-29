package com.example.java.domain.user.service;

import com.example.java.domain.user.domain.User;
import com.example.java.domain.user.facade.UserFacade;
import com.example.java.domain.user.presentation.dto.response.UserMyInfoResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyInfoService {
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserMyInfoResponse execute(){
        User user = userFacade.getCurrentUser();
        return new UserMyInfoResponse(user);
    }
}
