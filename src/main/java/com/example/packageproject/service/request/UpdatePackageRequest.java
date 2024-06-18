package com.example.packageproject.service.request;

import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UpdatePackageRequest {

    @NotNull
    private Long trackingNo;

    @NotNull
    private List<UpdatePackageImageRequest> images;

    public UpdatePackageRequest(Long trackingNo, List<UpdatePackageImageRequest> images) {
        this.trackingNo = trackingNo;
        this.images = images;
    }

    public List<PackageImage> toEntity(Package pkg) {
        return images.stream()
                .map(image -> image.toEntity(pkg))
                .collect(Collectors.toList());
    }


}
