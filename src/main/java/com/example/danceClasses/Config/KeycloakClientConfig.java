package com.example.danceClasses.Config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakClientConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:7080") // URL to your Keycloak server
                .realm("master") // realm to connect to
                .clientId("admin-cli") // client-id, typically 'admin-cli' for admin operations
                .username("admin") // admin username
                .password("Danceforever8*") // admin password
                .build();
    }
}
