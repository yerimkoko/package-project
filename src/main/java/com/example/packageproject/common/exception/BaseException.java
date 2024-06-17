package com.example.packageproject.common.exception;

import com.example.packageproject.common.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    protected BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
