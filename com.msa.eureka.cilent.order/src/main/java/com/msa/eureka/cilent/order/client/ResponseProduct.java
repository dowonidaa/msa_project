package com.msa.eureka.cilent.order.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseProduct {

    private Long id;
    private String name;
    private String description;
    private long price;
    private int quantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long buyerId;
}
