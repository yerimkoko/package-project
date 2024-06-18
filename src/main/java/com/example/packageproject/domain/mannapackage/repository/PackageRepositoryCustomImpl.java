package com.example.packageproject.domain.mannapackage.repository;

import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.packageproject.domain.mannapackage.QPackage.package$;
import static com.example.packageproject.domain.mannapackage.packageimage.QPackageImage.packageImage;


@RequiredArgsConstructor
public class PackageRepositoryCustomImpl implements PackageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Package findByPackageId(Long packageId) {
        return jpaQueryFactory.selectFrom(package$)
                .leftJoin(package$.packageImages, packageImage).fetchJoin()
                .where(package$.id.eq(packageId),
                        package$.status.eq(PackageStatus.ACTIVE))
                .fetchOne();
    }

    @Override
    public List<Package> findAllPackagesBySizeAndCursor(int size, Long cursor) {
        List<Long> packageIds = jpaQueryFactory.select(package$.id)
                .from(package$)
                .where(getCursor(cursor),
                        package$.status.eq(PackageStatus.ACTIVE))
                .orderBy(package$.id.desc())
                .limit(size)
                .fetch();

        return jpaQueryFactory.selectFrom(package$)
                .leftJoin(package$.packageImages, packageImage).fetchJoin()
                .where(package$.id.in(packageIds))
                .orderBy(package$.id.desc())
                .fetch();
    }

    @Override
    public Long existTrackingNo(Long trackingNo) {
        return jpaQueryFactory.select(package$.id)
                .from(package$)
                .where(package$.trackingNo.eq(trackingNo))
                .limit(1)
                .fetchOne();
    }


    private BooleanExpression getCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return package$.id.lt(cursor);
    }
}
