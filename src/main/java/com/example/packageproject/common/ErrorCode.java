package com.example.packageproject.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    E400_INVALID(400, "invalid_request", "잘못된 요청입니다."),
    E404_NOT_FOUND(404, "not_found", "찾을 수 없습니다"),
    E409_DUPLICATED(409, "conflict", "서버와 요청이 충돌했습니다");

    private final int status;

    private final String code;

    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
