package com.example.danceClasses.Config;

import com.example.danceClasses.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class BearerTokenInterceptor implements ClientHttpRequestInterceptor, HandlerInterceptor {

    private final AuthService authService;

    public BearerTokenInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = authService.getClientToken();
        request.getHeaders().set("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("BearerTokenInterceptor preHandle invoked");

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/swagger-ui") ||
                requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/swagger-resources") ||
                requestURI.startsWith("/webjars")) {
            return true; // Permite accesul fără a adăuga token-ul
        }

        // Obține tokenul clientului
        String clientToken = authService.getClientToken();

        // Verifică dacă antetul "Authorization" este deja prezent
        if (request.getHeader("Authorization") == null) {
            request.setAttribute("Authorization", "Bearer " + clientToken);
        }
        return true; // Continuă procesarea cererii
    }
}

