package me.study.springsecurity.core.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthRole {
    //    ROLE_ prefix 붙여야함 https://stackoverflow.com/questions/33205236/spring-security-added-prefix-role-to-all-roles-name
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private String value;
}
