package com.msa.eureka.cilent.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth")
public interface UserClient {

    @GetMapping("/auth/validated")
    Boolean validated(@RequestHeader("X-User_Id") Long userId, @RequestHeader("X-Role") String role);
}
