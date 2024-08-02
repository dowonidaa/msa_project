package com.msa.eureka.cilent.order;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/products")
    ResponseEntity<?> getProducts();

}
