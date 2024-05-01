package com.example.diary_sample.global.security.jwt;

import com.example.diary_sample.global.security.CustomUserDetailsService;
import com.example.diary_sample.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = request.getHeader("Authorization");

        if (!tokenValue.isBlank() && tokenValue.startsWith("Bearer ")) {
            String token = tokenValue.substring(7);

            if (jwtUtil.validateToken(token)) {
                String userId = jwtUtil.getUserId(token);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken resultToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(resultToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
