package com.example.packageproject.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    public static final ApiResponse<String> OK = success(null);

    private String error;

    private String message;

    private T data;

    public ApiResponse(String error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    @NotNull
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(null, null, data);
    }

    @NotNull
    public static <T> ApiResponse<T> error(ErrorCode code, String message) {
        return new ApiResponse<>(code.name(), message, null);
    }

}
