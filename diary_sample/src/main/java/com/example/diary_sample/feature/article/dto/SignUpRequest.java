package com.example.diary_sample.feature.article.dto;

import com.example.diary_sample.feature.article.domain.enums.JoinType;
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
