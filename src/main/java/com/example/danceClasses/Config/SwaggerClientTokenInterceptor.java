package com.example.danceClasses.Config;

import com.example.danceClasses.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class SwaggerClientTokenInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public SwaggerClientTokenInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getRequestURI().contains("/api")) {
            String clientToken = authService.getAccessToken(null, null, true);
            if (clientToken != null) {
                response.setHeader("Authorization", "Bearer " + clientToken);
            }
        }
        return true;
    }
}

