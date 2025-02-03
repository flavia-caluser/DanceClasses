package com.example.danceClasses.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestTemplateConfig implements WebMvcConfigurer {

    private final BearerTokenInterceptor bearerTokenInterceptor;

    public RestTemplateConfig(BearerTokenInterceptor bearerTokenInterceptor) {
        this.bearerTokenInterceptor = bearerTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(bearerTokenInterceptor);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(bearerTokenInterceptor);
        return restTemplate;
    }
}

