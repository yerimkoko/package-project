package com.example.packageproject.controller;

import com.example.packageproject.common.ApiResponse;
import com.example.packageproject.service.PackageService;
import com.example.packageproject.service.request.AddPackageRequest;
import com.example.packageproject.service.request.PackagesRequest;
import com.example.packageproject.service.request.UpdatePackageRequest;
import com.example.packageproject.service.response.PackageAndCursorResponse;
import com.example.packageproject.service.response.PackageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @PostMapping("/v1/package")
    public ApiResponse<Long> addPackage(@Valid @RequestBody AddPackageRequest request) {
        return ApiResponse.success(packageService.addPackage(request));
    }

    @GetMapping("/v1/package/{packageId}")
    public ApiResponse<PackageResponse> getPackages(@PathVariable Long packageId) {
        return ApiResponse.success(packageService.getPackage(packageId));
    }

    @GetMapping("/v1/packages")
    public ApiResponse<PackageAndCursorResponse> getPackages(@Valid PackagesRequest request) {

        return ApiResponse.success(packageService.getPackages(request.getSize(), request.getCursor()));

    }

    @DeleteMapping("/v1/package/{packageId}")
    public ApiResponse<String> deletePackage(@PathVariable Long packageId) {
        packageService.deletePackage(packageId);
        return ApiResponse.OK;
    }

    @PutMapping("/v1/package/{packageId}")
    public ApiResponse<String> updatePackage(@PathVariable Long packageId,
                                             @Valid @RequestBody UpdatePackageRequest request) {
        packageService.updatePackage(packageId, request);
        return ApiResponse.OK;
    }

}
