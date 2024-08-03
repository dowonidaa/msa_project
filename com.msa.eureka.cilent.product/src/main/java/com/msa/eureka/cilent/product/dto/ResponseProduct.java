package com.msa.eureka.cilent.product.dto;

import com.msa.eureka.cilent.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProduct {

    private String productMame;
    private long productPrice;
    private int productQuantity;

    public ResponseProduct(Product product) {
        this.productMame = product.getProductName();
        this.productPrice = product.getPrice();
        this.productQuantity = product.getQuantity();
    }
}
