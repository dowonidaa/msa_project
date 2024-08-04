package com.msa.eureka.cilent.order.entity;

import com.msa.eureka.cilent.order.dto.RequestOrder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @Column(name = "order_item_id")
    private List<Long> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String createBy;
    private String updaterBy;

    @Column(nullable = false)
    private Boolean isDelete = false;

    public Order(List<Long> orderItems, OrderStatus status, String username) {
        this.orderItems = orderItems;
        this.status = status;
        this.createBy = username;
    }

    public void delete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void update(RequestOrder request, String username) {
        this.orderItems = request.getOrderItems();
        this.status = OrderStatus.valueOf(request.getStatus());
        this.updaterBy = username;
    }
}
