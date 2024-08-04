package com.msa.eureka.cilent.order.dto;

import com.msa.eureka.cilent.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchOrder {

    private OrderStatus status;
    private List<Long> orderItems;
    private String sortBy;
}
