package com.msa.eureka.cilent.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RequestOrder {

    private List<Long> orderItems;
    private String status;

}
