package com.example.packageproject.service.request;

import com.example.packageproject.domain.mannapackage.Package;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AddPackageRequest {

    @NotNull
    private Long trackingNo;

    @NotNull
    private List<AddPackageImageRequest> images;

    public AddPackageRequest(Long trackingNo, List<AddPackageImageRequest> images) {
        this.trackingNo = trackingNo;
        this.images = images;
    }

    public Package toEntity() {
        Package pkg = Package.of(trackingNo);

        for (AddPackageImageRequest request : images) {
            pkg.add(request.toEntity(pkg));
        }

        return pkg;
    }


}
