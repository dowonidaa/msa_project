package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.dto.QResponseOrder;
import com.msa.eureka.cilent.order.dto.ResponseOrder;
import com.msa.eureka.cilent.order.dto.SearchOrder;
import com.msa.eureka.cilent.order.entity.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.msa.eureka.cilent.order.entity.QOrder.order;


@Repository

public class OrderSearchRepositoryImpl implements OrderSearchRepository {

    private final JPAQueryFactory queryFactory;

    public OrderSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResponseOrder> searchOrders(SearchOrder searchOrder, Pageable pageable, String username, String role) {
        List<ResponseOrder> responseOrders = queryFactory
                .select(new QResponseOrder(order))
                .from(order)
                .where(
                        statusEq(searchOrder.getStatus()),
                        orderItemsIn(searchOrder.getOrderItems()),
                        userCheck(role, username)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = getTotalCount(searchOrder, username, role);


        return new PageImpl<>(responseOrders, pageable, totalCount);
    }

    private Long getTotalCount(SearchOrder searchOrder, String username, String role) {
        return queryFactory
                .select(order.count())
                .from(order)
                .where(
                        statusEq(searchOrder.getStatus()),
                        orderItemsIn(searchOrder.getOrderItems()),
                        userCheck(role, username)
                ).fetchOne();
    }

    private BooleanExpression orderItemsIn(List<Long> orderItems) {
        return !orderItems.isEmpty() ? order.orderItems.any().in(orderItems) : null;
    }

    private BooleanExpression userCheck(String role, String username) {
        return role.equals("MEMBER") ? order.createBy.eq(username) : null;
    }

    private BooleanExpression statusEq(OrderStatus status) {
        return status != null ? order.status.eq(status) : null;
    }
}
