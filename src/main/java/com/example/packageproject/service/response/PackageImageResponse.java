package com.example.packageproject.service.response;

import com.example.packageproject.domain.mannapackage.PackageType;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PackageImageResponse {

    private PackageType packageType;

    private String fileName;

    @Builder
    private PackageImageResponse(PackageType packageType, String fileName) {
        this.packageType = packageType;
        this.fileName = fileName;
    }

    public static PackageImageResponse of(PackageImage image) {
        return PackageImageResponse.builder()
                .fileName(image.getFileName())
                .packageType(image.getPackageType())
                .build();
    }

}
