package com.example.packageproject.common.exception;

import com.example.packageproject.common.ErrorCode;
import lombok.Getter;

@Getter
public class ConflictException extends BaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_DUPLICATED;

    public ConflictException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }
}
