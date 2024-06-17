package com.example.packageproject.service.response;

import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PackageResponse {

    private Long id;

    private Long trackingNo;

    private List<PackageImageResponse> images;

    @Builder
    public PackageResponse(Long id, Long trackingNo, List<PackageImageResponse> images) {
        this.id = id;
        this.trackingNo = trackingNo;
        this.images = images;
    }

    public static PackageResponse of(Package pkg) {
        return PackageResponse.builder()
                .id(pkg.getId())
                .trackingNo(pkg.getTrackingNo())
                .images(getImages(pkg))
                .build();
    }

    private static List<PackageImageResponse> getImages(Package pkg) {
        return pkg.getPackageImages().stream()
                .map(PackageImageResponse::of)
                .collect(Collectors.toList());
    }


}
