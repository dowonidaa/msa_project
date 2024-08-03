package com.msa.eureka.cilent.product.entity;

import com.msa.eureka.cilent.product.dto.RequestProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private long price;


    public Product(RequestProduct request) {
        this.productName = request.getProductName();
        this.quantity = request.getProductQuantity();
        this.price = request.getProductPrice();
    }

    public void update(RequestProduct request) {
        this.price = request.getProductPrice();
        this.quantity = request.getProductQuantity();
        this.productName = request.getProductName();
    }
}
