package com.example.java.domain.user.presentation;

import com.example.java.domain.user.presentation.dto.request.UpdateMyInfoRequest;
import com.example.java.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.example.java.domain.user.presentation.dto.request.UserLoginRequest;
import com.example.java.domain.user.presentation.dto.request.UserSignupRequest;
import com.example.java.domain.user.presentation.dto.response.UserMyInfoResponse;
import com.example.java.domain.user.service.*;
import com.example.java.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignupUserService signupUserService;
    private final LoginUserService loginUserService;
    private final UpdateMyInfoService updateMyInfoService;
    private final UpdatePasswordService updatePasswordService;
    private final MyInfoService myInfoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signupUser(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        signupUserService.execute(userSignupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return loginUserService.execute(userLoginRequest);
    }

    @PatchMapping
    public void updateMyInfo(@RequestBody UpdateMyInfoRequest updateMyInfoRequest) {
        updateMyInfoService.execute(updateMyInfoRequest);
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        updatePasswordService.execute(updatePasswordRequest);
    }

    @GetMapping
    public UserMyInfoResponse myInfo() {
        return myInfoService.execute();
    }
}
