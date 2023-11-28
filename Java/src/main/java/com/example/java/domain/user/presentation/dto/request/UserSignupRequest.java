package com.example.java.domain.user.presentation.dto.request;

import com.example.java.domain.user.domain.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSignupRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private Sex sex;
}
