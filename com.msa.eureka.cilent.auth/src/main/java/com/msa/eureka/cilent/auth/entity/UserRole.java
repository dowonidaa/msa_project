package com.msa.eureka.cilent.auth.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    MEMBER(Authority.MEMBER), MANAGER(Authority.MANAGER);

    private String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public static class Authority{
        public static final String MEMBER = "ROLE_MEMBER";
        public static final String MANAGER = "ROLE_MANAGER";
    }
}
