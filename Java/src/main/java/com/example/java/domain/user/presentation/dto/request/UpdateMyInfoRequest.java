package com.example.java.domain.user.presentation.dto.request;

import com.example.java.domain.user.domain.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMyInfoRequest {

    private String accountId;

    private String name;

    private Sex sex;
}
