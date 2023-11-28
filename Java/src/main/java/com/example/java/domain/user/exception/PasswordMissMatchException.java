package com.example.java.domain.user.exception;

import com.example.java.global.error.exception.BusinessException;
import com.example.java.global.error.exception.ErrorCode;

public class PasswordMissMatchException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new PasswordMissMatchException();

    private PasswordMissMatchException() {super(ErrorCode.PASSWORD_MISS_MATCH);}
}
