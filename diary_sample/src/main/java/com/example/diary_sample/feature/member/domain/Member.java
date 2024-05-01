package com.example.diary_sample.feature.member.domain;

import com.example.diary_sample.feature.member.domain.enums.JoinType;
import com.example.diary_sample.feature.member.domain.enums.MemberRole;
import com.example.diary_sample.feature.member.dto.SignUpRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String cellPhone;
    private String nickName;
    @Enumerated(EnumType.STRING)
    private JoinType joinType;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    private Member(String name, String email, String password, String cellPhone, String nickName, JoinType joinType, MemberRole memberRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cellPhone = cellPhone;
        this.nickName = nickName;
        this.joinType = joinType;
        this.memberRole = memberRole;
    }

    public static Member of(SignUpRequest request) {
        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .joinType(request.getJoinType())
                .nickName(request.getNickName())
                .cellPhone(request.getCellPhone())
                .memberRole(request.getMemberRole())
                .build();
    }

}
