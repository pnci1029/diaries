package com.example.diary_sample.global.security;

import com.example.diary_sample.global.security.jwt.JwtAuthFilter;
import com.example.diary_sample.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final String[] AUTH_LIST = {
            "/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // cors, csrf, formLogin
//        http.formLogin((form) -> form.disable()); // 람다 처리
        http.formLogin(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 스프링 시큐리티가 세션 사용x, 상태 없음 구성
        http.sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
        ));

        // JwtAuthFilter UserName뭐시기 앞에 배치
        http.addFilterBefore(new JwtAuthFilter(jwtUtil, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        // 권한 규칙
        http.authorizeRequests(authorize ->
                authorize.requestMatchers(AUTH_LIST).permitAll()
                        .anyRequest().permitAll()
        );

        // 커스텀 익셉션 핸들링 구현
        http.exceptionHandling((exception) ->
                exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
        );

        return http.build();
    }
}
