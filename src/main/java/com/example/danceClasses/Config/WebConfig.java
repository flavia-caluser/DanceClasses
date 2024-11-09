package com.example.danceClasses.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SwaggerClientTokenInterceptor swaggerClientTokenInterceptor;

    public WebConfig(SwaggerClientTokenInterceptor swaggerClientTokenInterceptor) {
        this.swaggerClientTokenInterceptor = swaggerClientTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(swaggerClientTokenInterceptor)
                .addPathPatterns("/v3/api-docs/**", "/swagger-ui/**");
    }
}

