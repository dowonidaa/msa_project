package com.msa.eureka.cilent.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseOrderItem {

    private Long orderItemId;
    private String name;
    private long price;
    private int quantity;

}
