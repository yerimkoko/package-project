package com.example.packageproject.domain.mannapackage.packageimage;

import com.example.packageproject.domain.BaseEntity;
import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PackageImage extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @Column(nullable = false, length = 200)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Package pkg;

    @Builder
    private PackageImage(PackageType packageType, String fileName, Package pkg) {
        this.packageType = packageType;
        this.fileName = fileName;
        this.pkg = pkg;
    }

    public static PackageImage of(@NotNull Package pkg, @NotNull PackageType packageType, @NotBlank String fileName) {
        return PackageImage.builder()
                .fileName(fileName)
                .pkg(pkg)
                .packageType(packageType)
                .build();
    }
}
