package me.study.springsecurity.core.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;
}
