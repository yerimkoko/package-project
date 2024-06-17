package com.example.packageproject.domain.mannapackage.repository;

import com.example.packageproject.domain.mannapackage.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long>, PackageRepositoryCustom {

}
