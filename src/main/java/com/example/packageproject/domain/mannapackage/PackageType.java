package com.example.packageproject.domain.mannapackage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageType {
    PKG("패키지")
    ;

    private final String description;

}
