package com.msa.eureka.cilent.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface UserClient {

    @GetMapping("/auth/validated")
    Boolean validated(@RequestParam String username, @RequestParam String role);
}
