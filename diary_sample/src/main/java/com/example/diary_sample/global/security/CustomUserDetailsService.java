package com.example.diary_sample.global.security;

import com.example.diary_sample.feature.member.domain.Member;
import com.example.diary_sample.feature.member.domain.MemberRepository;
import com.example.diary_sample.feature.member.dto.MemberInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ObjectMapper om;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> target = memberRepository.findById(Long.parseLong(id));
        if (target.isEmpty()) {
            throw new UsernameNotFoundException("해당하는 유저가 없읍니다.");
        }

        MemberInfo result = om.convertValue(target.get(), MemberInfo.class);
        return new CustomUserDetails(result);
    }
}
