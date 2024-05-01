package com.example.diary_sample.global.security;

import com.example.diary_sample.global.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component @RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper om;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response<Object> result = Response.result(null, HttpStatus.FORBIDDEN, accessDeniedException.getMessage());

        String responseValue = om.writeValueAsString(result);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(403);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseValue);
    }
}
