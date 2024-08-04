package com.msa.eureka.cilent.product.dto;

import com.msa.eureka.cilent.product.entity.Product;
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


    public ResponseProduct(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
        this.productQuantity = product.getQuantity();
        this.createAt = product.getCreateAt();
        this.updateAt = product.getUpdateAt();
    }
}
