package com.example.packageproject.service.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackagesRequest {

    @Nullable
    private Long cursor;

    @Max(value = 30)
    @Positive
    private int size;

    private PackagesRequest(Long cursor, int size) {
        this.cursor = cursor;
        this.size = size;
    }

}
