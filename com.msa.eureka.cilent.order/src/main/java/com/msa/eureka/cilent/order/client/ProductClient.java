package com.msa.eureka.cilent.order.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/products")
    ResponseEntity<?> getProducts();

    @GetMapping("/products/{id}")
    ResponseEntity<ResponseProduct> getProductById(@PathVariable("id") Long productId);

    @GetMapping("/products/{id}/reduceQuantity")
    void reduceQuantity(Long productId, int quantity);
}
