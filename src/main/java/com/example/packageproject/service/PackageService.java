package com.example.packageproject.service;

import com.example.packageproject.common.exception.ConflictException;
import com.example.packageproject.common.exception.NotFoundException;
import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.repository.PackageRepository;
import com.example.packageproject.service.request.AddPackageRequest;
import com.example.packageproject.service.request.UpdatePackageRequest;
import com.example.packageproject.service.response.PackageAndCursorResponse;
import com.example.packageproject.service.response.PackageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    @Transactional
    public Long addPackage(AddPackageRequest request) {
        validatePackage(request.getTrackingNo());
        Package pkg = packageRepository.save(request.toEntity());
        return pkg.getId();
    }

    @Transactional
    public void deletePackage(Long packageId) {
        Package pkg = findByPackageId(packageId);
        pkg.delete();
    }


    @Transactional
    public void updatePackage(Long packageId,
                              UpdatePackageRequest request) {
        validatePackage(request.getTrackingNo());
        Package pkg = findByPackageId(packageId);
        pkg.update(request.getTrackingNo(), request.toEntity(pkg));
    }

    @Transactional(readOnly = true)
    public PackageResponse getPackage(Long packageId) {
        Package pkg = findByPackageId(packageId);
        return PackageResponse.of(pkg);
    }

    @Transactional(readOnly = true)
    public PackageAndCursorResponse getPackages(int size, Long cursor) {
        List<Package> packages = packageRepository.findAllPackagesBySizeAndCursor(size + 1, cursor);
        if (packages.isEmpty() || packages.size() <= size) {
            return PackageAndCursorResponse.noMore(packages);
        }
        return PackageAndCursorResponse.hasNext(packages);
    }

    private void validatePackage(Long trackingNo) {
        Long validatePackage = packageRepository.existTrackingNo(trackingNo);
        if (validatePackage != null) {
            throw new ConflictException(String.format("(%s)는 이미 존재하는 운송장 번호 입니다.", trackingNo));
        }
    }

    private Package findByPackageId(Long packageId) {
        Package pkg = packageRepository.findByPackageId(packageId);
        if (pkg == null) {
            throw new NotFoundException(String.format("(%s) 에 해당하는 packageId는 존재하지 않습니다.", packageId));
        }
        return pkg;
    }

}
