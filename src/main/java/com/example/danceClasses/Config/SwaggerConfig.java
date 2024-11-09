package com.example.danceClasses.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SwaggerConfig {
    //TODO: de facut modificari in swagger sa se inteleaga mai bine ce e de facut
    // sa testez ca totul merge
    // sa fac clientul autentificat by default
    @Bean
    @Primary
    public OpenAPI customOpenAPI() {
        final String oauth2SchemeName = "OAuth2";
        final String bearerSchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info().title("Dance classes - REST APIs").version("v1"))
                .addSecurityItem(new SecurityRequirement().addList(oauth2SchemeName))
                .addSecurityItem(new SecurityRequirement().addList(bearerSchemeName))
                .components(new Components()
                        .addSecuritySchemes(oauth2SchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("http://localhost:7080/realms/master/protocol/openid-connect/auth")
                                                .tokenUrl("http://localhost:7080/realms/master/protocol/openid-connect/token")
                                                .scopes(new Scopes()
                                                        .addString("read", "read access")
                                                        .addString("write", "write access"))
                                        )
                                )
                        )
                        .addSecuritySchemes(bearerSchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );
    }



}
