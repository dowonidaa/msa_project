package com.msa.eureka.cilent.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProduct {

    private String description;
    private String productName;
    private long productPrice;
    private int productQuantity;
}
