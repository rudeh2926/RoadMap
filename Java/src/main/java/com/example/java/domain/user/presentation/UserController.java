package com.example.java.domain.user.presentation;

import com.example.java.domain.user.presentation.dto.request.UserSignupRequest;
import com.example.java.domain.user.service.SignupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignupUserService signupUserService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signupUser(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        signupUserService.execute(userSignupRequest);
    }
}
