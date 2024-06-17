package com.example.packageproject.service.request;

import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageType;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePackageImageRequest {

    @NotNull
    private PackageType packageType;

    @NotBlank
    private String fileName;

    public UpdatePackageImageRequest(PackageType packageType, String fileName) {
        this.packageType = packageType;
        this.fileName = fileName;
    }

    public PackageImage toEntity(Package pkg) {
        return PackageImage.of(pkg, packageType, fileName);
    }
}
