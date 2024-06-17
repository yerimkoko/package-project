package com.example.packageproject.service.response;

import com.example.packageproject.domain.mannapackage.Package;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PackageAndCursorResponse {

    private CursorResponse cursor;

    private List<PackageResponse> packages;

    @Builder
    public PackageAndCursorResponse(CursorResponse cursor, List<PackageResponse> packages) {
        this.cursor = cursor;
        this.packages = packages;
    }


    public static PackageAndCursorResponse hasNext(List<Package> packages) {
        return PackageAndCursorResponse.builder()
                .cursor(CursorResponse.hasNext(packages))
                .packages(getPackages(packages))
                .build();

    }

    public static PackageAndCursorResponse noMore(List<Package> packages) {
        return PackageAndCursorResponse.builder()
                .cursor(CursorResponse.noMore())
                .packages(getPackages(packages))
                .build();
    }


    private static List<PackageResponse> getPackages(List<Package> packages) {
        return packages.stream()
                .map(PackageResponse::of)
                .collect(Collectors.toList());
    }

}
