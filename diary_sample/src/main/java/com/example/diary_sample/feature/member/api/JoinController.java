package com.example.diary_sample.feature.member.api;

import com.example.diary_sample.feature.member.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/auth/join/v1")
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/")
    public void join(@RequestBody JoinRequest request) {
        joinService.join(request);
    }
}
