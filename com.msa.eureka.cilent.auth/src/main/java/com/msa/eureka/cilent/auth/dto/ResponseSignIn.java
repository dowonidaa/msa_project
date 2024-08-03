package com.msa.eureka.cilent.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSignIn {

    private String token;

    public ResponseSignIn(String token) {
        this.token = token;
    }
}
