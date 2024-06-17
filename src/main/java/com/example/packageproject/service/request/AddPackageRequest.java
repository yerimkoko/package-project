package com.example.packageproject.service.request;

import com.example.packageproject.domain.mannapackage.Package;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPackageRequest {

    @NotNull
    private Long trackingNo;

    private List<AddPackageImageRequest> images;

    public Package toEntity() {
        Package pkg = Package.of(trackingNo);

        for (AddPackageImageRequest request : images) {
            pkg.add(request.toEntity(pkg));
        }

        return pkg;
    }
}
