package com.example.packageproject.domain.mannapackage.repository;


import com.example.packageproject.domain.mannapackage.Package;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface PackageRepositoryCustom {

    Package findByPackageId(@NotNull Long packageId);

    List<Package> findAllPackagesBySizeAndCursor(int size,
                                                 @Nullable Long cursor);

    Long existTrackingNo(Long trackingNo);


}
