package com.msa.eureka.cilent.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSignIn {

    private String username;
    private String password;
}
