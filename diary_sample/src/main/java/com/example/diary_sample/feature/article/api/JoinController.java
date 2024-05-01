package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/auth/signup/v1")
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/")
    public void signIn(@RequestBody SignUpRequest request) {
        joinService.signUp(request);
    }
}
