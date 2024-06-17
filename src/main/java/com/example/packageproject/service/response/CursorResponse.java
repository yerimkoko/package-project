package com.example.packageproject.service.response;

import com.example.packageproject.domain.mannapackage.Package;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static CursorResponse hasNext(List<Package> packages) {
        int lastIndex = Math.max(packages.size() - 1, 0);
        Long nextCursor = packages.get(lastIndex).getId();
        return CursorResponse.builder()
                .hasNext(true)
                .nextCursor(nextCursor)
                .build();

    }

}
