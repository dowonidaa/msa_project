package com.msa.eureka.cilent.order.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseProduct {

    private Long productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private int productQuantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
