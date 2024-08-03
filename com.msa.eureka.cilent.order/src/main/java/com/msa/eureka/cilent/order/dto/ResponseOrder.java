package com.msa.eureka.cilent.order.dto;

import com.msa.eureka.cilent.order.entity.Order;
import com.msa.eureka.cilent.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ResponseOrder {

    private Long orderId;
    private String status;
    private LocalDateTime createAt;
    private List<Long> orderItems;
    private LocalDateTime updateAt;



    public ResponseOrder(Order order) {
        this.orderId = order.getId();
        this.status = order.getStatus().name();
        this.createAt = order.getCreateAt();
        this.orderItems = order.getOrderItems();
        this.updateAt = order.getUpdateAt();
    }
}
