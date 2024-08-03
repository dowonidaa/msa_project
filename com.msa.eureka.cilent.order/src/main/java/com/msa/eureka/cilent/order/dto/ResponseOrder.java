package com.msa.eureka.cilent.order.dto;

import com.msa.eureka.cilent.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseOrder {

    private
    private LocalDateTime createAt;

    public ResponseOrder(Order order) {

    }
}
