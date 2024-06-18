package com.example.packageproject.service.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackagesRequest {

    @Nullable
    private Long cursor;

    @Max(30)
    @Min(0)
    private int size;

    private PackagesRequest(Long cursor, int size) {
        this.cursor = cursor;
        this.size = size;
    }

}
