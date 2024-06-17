package com.example.packageproject.service;

import com.example.packageproject.IntegrationTest;
import com.example.packageproject.common.exception.NotFoundException;
import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageStatus;
import com.example.packageproject.domain.mannapackage.PackageType;
import com.example.packageproject.domain.mannapackage.repository.PackageRepository;
import com.example.packageproject.service.request.AddPackageRequest;
import com.example.packageproject.service.request.AddPackageImageRequest;
import com.example.packageproject.service.request.UpdatePackageImageRequest;
import com.example.packageproject.service.request.UpdatePackageRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PackageServiceTest extends IntegrationTest {

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @AfterEach
    void clean_up() {
        packageRepository.deleteAll();
    }


    @Test
    void 패키지를_추가한다() {
        // given
        PackageType packageType = PackageType.PKG;
        String fileName = "image1.png";
        Long trackingNo = 111122223333L;
        AddPackageImageRequest imageRequest = new AddPackageImageRequest(packageType, fileName);
        AddPackageRequest request = new AddPackageRequest(trackingNo, List.of(imageRequest));

        // when
        packageService.addPackage(request);

        // then
        List<Package> aPackages = packageRepository.findAll();
        assertThat(aPackages).hasSize(1);

    }

    @Test
    void 패키지를_제거한다() {
        // given
        Package pkg = createPackage();

        // when
        packageService.deletePackage(pkg.getId());

        // then
        List<Package> pkgList = packageRepository.findAll();
        assertThat(pkgList).hasSize(1);
        assertThat(pkgList.get(0).getStatus()).isEqualTo(PackageStatus.DELETED);

    }

    @Test
    void 존재하지_않는_패키지를_지우려고_한다() {
        // given
        Package pkg = createPackage();

        // when & then
        packageService.deletePackage(pkg.getId());
        assertThatThrownBy(() -> packageService.deletePackage(pkg.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void 패키지를_수정할때_존재하지_않는_패키지일때() {
        // given
        Package pkg = createPackage();
        PackageType packageType = PackageType.PKG;
        String fileName = "image333.jpg";
        UpdatePackageImageRequest updatePackageImageRequest = new UpdatePackageImageRequest(packageType, fileName);
        UpdatePackageRequest request = new UpdatePackageRequest(pkg.getTrackingNo(), List.of(updatePackageImageRequest));

        // when & then
        assertThatThrownBy(() -> packageService.updatePackage(pkg.getId() + 999L, request))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void 패키지를_수정한다() {
        // given
        Package pkg = createPackage();
        Long trackingNo = 123L;
        PackageType packageType = PackageType.PKG;
        String fileName = "updatedName";
        UpdatePackageImageRequest imageRequest = new UpdatePackageImageRequest(packageType, fileName);
        UpdatePackageRequest request = new UpdatePackageRequest(trackingNo, List.of(imageRequest));

        // when
        packageService.updatePackage(pkg.getId(), request);

        // then
        List<Package> pkgList = packageRepository.findAll();
        assertThat(pkgList.get(0).getTrackingNo()).isEqualTo(trackingNo);

    }

    private Package createPackage() {
        Long trackingNo = 9L;
        return packageRepository.save(Package.of(trackingNo));
    }

}
