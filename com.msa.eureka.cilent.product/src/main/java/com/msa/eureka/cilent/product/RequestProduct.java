package com.msa.eureka.cilent.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProduct {

    private Long id;
    private String productName;
    private long productPrice;
    private int productQuantity;
}
