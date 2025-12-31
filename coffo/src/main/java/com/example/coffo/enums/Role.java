package com.example.coffo.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


@Getter
public enum Role implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
