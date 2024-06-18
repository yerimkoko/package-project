package com.example.packageproject.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CursorResponse {

    private Long nextCursor;

    private boolean hasNext;

    @Builder
    public CursorResponse(Long nextCursor, boolean hasNext) {
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
    }

    public static CursorResponse noMore() {
        return CursorResponse.builder()
                .hasNext(false)
                .nextCursor(null)
                .build();
    }

}
