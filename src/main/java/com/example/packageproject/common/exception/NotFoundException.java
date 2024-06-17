package com.example.packageproject.common.exception;

import com.example.packageproject.common.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException{

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E404_NOT_FOUND;

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }
}
