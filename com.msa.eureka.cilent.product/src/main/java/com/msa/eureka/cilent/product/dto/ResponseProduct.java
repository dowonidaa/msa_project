package com.msa.eureka.cilent.product.dto;

import com.msa.eureka.cilent.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProduct {

    private Long productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private int productQuantity;

    public ResponseProduct(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
        this.productQuantity = product.getQuantity();
    }
}
