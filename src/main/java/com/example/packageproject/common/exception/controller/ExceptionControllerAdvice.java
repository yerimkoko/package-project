package com.example.packageproject.common.exception.controller;

import com.example.packageproject.common.ApiResponse;
import com.example.packageproject.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getErrorCode().getMessage(), e);
        return ApiResponse.error(e.getErrorCode(), e.getMessage());
    }
}
