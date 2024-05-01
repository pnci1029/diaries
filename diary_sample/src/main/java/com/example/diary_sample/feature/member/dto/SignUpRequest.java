package com.example.diary_sample.feature.member.dto;

import com.example.diary_sample.feature.member.domain.enums.JoinType;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private String cellPhone;
    private String nickName;
    private JoinType joinType;
}
