package com.example.danceClasses.Service;

import com.example.danceClasses.Config.KeycloakRoleConverter;
import com.example.danceClasses.DTOS.UserRequestDTO;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class AuthService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak-admin.username}")
    private String adminUsername;

    @Value("${keycloak-admin.password}")
    private String adminPassword;
    private KeycloakRoleConverter keycloakRoleConverter;

    public void registerUser(UserRequestDTO userRequestDTO) {
        Keycloak keycloakAdmin = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(adminUsername)
                .password(adminPassword)
                .build();
        String username = userRequestDTO.getUsername();
        String password = userRequestDTO.getPassword();
        String role = userRequestDTO.getRole();

        // Creare credențiale pentru noul utilizator
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        System.out.println("Creare reprezentare utilizator");
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        System.out.println("Înregistrare utilizator în Keycloak");
        Response response = keycloakAdmin.realm(realm).users().create(user);
        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            // Asignare rol
            assignRoleToUser(keycloakAdmin, realm, userId, role);

        } else {
            String errorMessage = response.readEntity(String.class);
            response.close();
            throw new RuntimeException("Failed to register user. Response: " + response.getStatus() + " - " + errorMessage);
        }
    }

    private void assignRoleToUser(Keycloak keycloak, String realm, String userId, String roleName) {
        var role = keycloak.realm(realm).roles().get(roleName).toRepresentation();
        var userResource = keycloak.realm(realm).users().get(userId);
        userResource.roles().realmLevel().add(Collections.singletonList(role));
    }

    public String getAccessToken(String username, String password, boolean isClientCredentials) {
        Keycloak keycloak;

        if (isClientCredentials) {
            // Configurare pentru client_credentials
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakServerUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        } else {
            // Configurare pentru password (autentificare utilizator)
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakServerUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .username(username)
                    .password(password)
                    .build();
        }

        return keycloak.tokenManager().getAccessTokenString();
    }


}


