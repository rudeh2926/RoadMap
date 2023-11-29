package com.example.java.domain.user.presentation.dto.response;

import com.example.java.domain.user.domain.Sex;
import com.example.java.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMyInfoResponse {

    private String accountId;

    private String name;

    private Sex sex;

    public UserMyInfoResponse(User user) {
        accountId = user.getAccountId();
        name = user.getName();
        sex = user.getSex();
    }
}
