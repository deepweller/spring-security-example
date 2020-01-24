package me.study.springsecurity.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String memberId;
    private String memberName;
    private String memberPassword;
}
