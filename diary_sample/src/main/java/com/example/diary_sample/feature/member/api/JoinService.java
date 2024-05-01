package com.example.diary_sample.feature.member.api;

import com.example.diary_sample.feature.member.domain.Member;
import com.example.diary_sample.feature.member.domain.MemberRepository;
import com.example.diary_sample.feature.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Slf4j
@Transactional(readOnly = true)
public class JoinService {
    private final MemberRepository memberRepository;

    public void signUp(SignUpRequest request) {
        memberRepository.save(Member.of(request));
    }
}
