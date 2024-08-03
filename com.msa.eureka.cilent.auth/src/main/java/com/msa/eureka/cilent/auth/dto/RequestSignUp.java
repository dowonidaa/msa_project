package com.msa.eureka.cilent.auth.dto;

import com.msa.eureka.cilent.auth.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSignUp {

    private String username;
    private String password;
    private String email;
    private UserRole role;
}
