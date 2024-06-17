package com.example.packageproject.domain.mannapackage.repository;

import com.example.packageproject.domain.mannapackage.Package;
import com.example.packageproject.domain.mannapackage.PackageStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.packageproject.domain.mannapackage.QPackage.package$;


@RequiredArgsConstructor
public class PackageRepositoryCustomImpl implements PackageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Package findByPackageId(Long packageId) {
        return jpaQueryFactory.selectFrom(package$)
                .where(package$.id.eq(packageId),
                        package$.status.eq(PackageStatus.ACTIVE))
                .fetchOne();
    }

    @Override
    public List<Package> findAllPackagesBySizeAndCursor(int size, Long cursor) {
        return jpaQueryFactory.selectFrom(package$)
                .where(getCursor(cursor),
                        package$.status.eq(PackageStatus.ACTIVE))
                .orderBy(package$.id.desc())
                .limit(size)
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
