package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.dto.QResponseProduct;
import com.msa.eureka.cilent.product.dto.ResponseProduct;
import com.msa.eureka.cilent.product.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.msa.eureka.cilent.product.entity.QProduct.*;

public class ProductRepositoryImpl implements ProductCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResponseProduct> searchProduct(Pageable pageable, String username, String role) {
        List<ResponseProduct> responseProducts = queryFactory
                .select(new QResponseProduct(product))
                .from(product)
                .where(

                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(product.count())
                .from(product)
                .where(

                ).fetchOne();

        return new PageImpl<>(responseProducts, pageable, totalCount);
    }
}
