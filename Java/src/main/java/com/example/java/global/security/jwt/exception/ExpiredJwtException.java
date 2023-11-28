package com.example.java.global.security.jwt.exception;

import com.example.java.global.error.exception.ErrorCode;
import com.example.java.global.error.exception.BusinessException;

public class ExpiredJwtException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}