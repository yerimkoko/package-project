package com.example.packageproject.domain.mannapackage;

import com.example.packageproject.domain.BaseEntity;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Package extends BaseEntity {

    @Column(nullable = false, length = 200)
    private Long trackingNo;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;

    @OneToMany(mappedBy = "pkg", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PackageImage> packageImages = new ArrayList<>();


    @Builder
    public Package(Long trackingNo, PackageStatus status) {
        this.trackingNo = trackingNo;
        this.status = status;
    }

    public void add(PackageImage image) {
        this.packageImages.add(image);
    }


    public void delete() {
        this.status = PackageStatus.DELETED;
    }

    public void update(Long trackingNo, List<PackageImage> images) {
        packageImages.clear();
        this.trackingNo = trackingNo;
        this.packageImages.addAll(images);
    }

    public static Package of(Long trackingNo) {
        return Package.builder()
                .trackingNo(trackingNo)
                .status(PackageStatus.ACTIVE)
                .build();
    }
}
