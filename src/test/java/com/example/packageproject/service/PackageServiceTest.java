package com.example.packageproject.service;

import com.example.packageproject.IntegrationTest;
import com.example.packageproject.common.exception.ConflictException;
import com.example.packageproject.common.exception.NotFoundException;
import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageStatus;
import com.example.packageproject.domain.mannapackage.PackageType;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImage;
import com.example.packageproject.domain.mannapackage.packageimage.PackageImageRepository;
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

    @Autowired
    private PackageImageRepository packageImageRepository;


    @AfterEach
    void cleanUp() {
        packageRepository.deleteAll();
    }

    @Test
    void 패키지를_추가합니다() {
        // given
        PackageType packageType = PackageType.PKG;
        String fileName = "image1.png";
        Long trackingNo = 111122223333L;
        AddPackageImageRequest imageRequest = new AddPackageImageRequest(packageType, fileName);
        AddPackageRequest request = new AddPackageRequest(trackingNo, List.of(imageRequest));

        // when
        packageService.addPackage(request);

        // then
        List<Package> packageList = packageRepository.findAll();
        List<PackageImage> packageImages = packageImageRepository.findAll();

        assertThat(packageList).hasSize(1);
        assertPackage(packageList.get(0), trackingNo);

        assertThat(packageImages).hasSize(1);
        assertPackageImage(packageImages.get(0), fileName, packageType);

    }

    @Test
    void 패키지를_추가할때_이미_존재하는_운송장번호는_추가할_수_없습니다() {
        // given
        Package pkg = createPackage();
        AddPackageRequest request = new AddPackageRequest(pkg.getTrackingNo(), null);

        // when & then
        assertThatThrownBy(() -> packageService.addPackage(request))
                .isInstanceOf(ConflictException.class);

    }

    @Test
    void 패키지를_수정할때_이미_존재하는_운송장번호로는_변경할_수_없습니다() {
        // given
        Package pkg = createPackage();
        UpdatePackageRequest request = new UpdatePackageRequest(pkg.getTrackingNo(), null);

        // when & then
        assertThatThrownBy(() -> packageService.updatePackage(pkg.getId(), request))
                .isInstanceOf(ConflictException.class);
    }


    @Test
    void 패키지를_제거하면_상태가_DELETED_로_변경됩니다() {
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
    void 존재하지_않는_패키지를_지울_수_없습니다() {
        // when & then
        assertThatThrownBy(() -> packageService.deletePackage(-1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void 존재하지_않는_패키지를_수정할_수_없습니다() {
        // given
        PackageType packageType = PackageType.PKG;
        String fileName = "image333.jpg";
        UpdatePackageImageRequest updatePackageImageRequest = new UpdatePackageImageRequest(packageType, fileName);
        Long trackingNo = 123123L;
        UpdatePackageRequest request = new UpdatePackageRequest(trackingNo, List.of(updatePackageImageRequest));

        // when & then
        assertThatThrownBy(() -> packageService.updatePackage(-1L, request))
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

    private void assertPackage(Package pkg, Long trackingNo) {
        assertThat(pkg.getTrackingNo()).isEqualTo(trackingNo);
    }

    private void assertPackageImage(PackageImage image, String fileName, PackageType packageType) {
        assertThat(image.getFileName()).isEqualTo(fileName);
        assertThat(image.getPackageType()).isEqualTo(packageType);
    }

}
