package com.example.java.domain.user.exception;

import com.example.java.global.error.exception.BusinessException;
import com.example.java.global.error.exception.ErrorCode;

public class AccountExistsException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new AccountExistsException();

    private AccountExistsException() {super(ErrorCode.ACCOUNTID_ALREADY_EXISTS);}
}
