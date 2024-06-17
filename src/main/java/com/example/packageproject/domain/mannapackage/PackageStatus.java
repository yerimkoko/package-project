package com.example.packageproject.domain.mannapackage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageStatus {

    ACTIVE("활성화"),
    DELETED("삭제됨")
    ;

    private final String description;


}
