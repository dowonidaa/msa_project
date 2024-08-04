package com.msa.eureka.cilent.order.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    ResponseEntity<?> getProducts();

    @GetMapping("/products/{productId}")
    ResponseEntity<ResponseProduct> getProductById(@PathVariable Long productId);

    @GetMapping("/products/{productId}/reduceQuantity")
    ResponseEntity<?> reduceQuantity(@PathVariable Long productId,@RequestParam int quantity);
}
