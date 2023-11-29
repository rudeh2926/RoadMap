package com.example.java.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank
    private String validPassword;
    @NotBlank
    private String newPassword;
}
