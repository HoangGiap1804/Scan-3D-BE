package com.example.scan3d.config;

import com.example.scan3d.models.response.ErrorResponse;
import com.example.scan3d.models.response.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(StatusCode.UNAUTHORIZED.getCode());

        log.info("Unauthorized - please login");

        ErrorResponse errorResponse = new ErrorResponse("Unauthorized - Please login");
        String json = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
