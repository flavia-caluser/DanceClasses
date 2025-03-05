package com.example.danceClasses.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //TODO: sa modific response body-ul la getAttendances sa se vada si la ce curs ii fiecare prezenta
    // pt fiecare cursant sa se afiseze cursurile(mapa)
    // la getRevenues sa-i dau un debug
    // sa fac un mapper pt paymentResponseDTO
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security", 
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/auth/**"

    };

    private Converter<Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new KeycloakRoleConverter());
        return jwtAuthenticationConverter;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers("/auth/token").permitAll()
                                .requestMatchers("/attendance/add/**").hasRole("Instructor")
                                .requestMatchers("/attendance/{id}").hasRole("Instructor")
                                .requestMatchers("/course/add").hasRole("Instructor")
                                .requestMatchers("/course/addLesson").hasRole("Instructor")
                                .requestMatchers("/course/addReview/**").hasRole("Student")
                                .requestMatchers("/course/getRevenues/**").hasRole("Instructor")
                                .requestMatchers("/course/change/**").hasRole("Instructor")
                                .requestMatchers("/course/{id}").hasRole("Instructor")
                                .requestMatchers("/course/deleteLesson/**").hasRole("Instructor")
                                .requestMatchers("/course/deleteReview/**").hasRole("Student")
                                .requestMatchers("/instructor/**").hasRole("Instructor")
                                .requestMatchers("/payment/{id}").hasRole("Instructor")
                                .requestMatchers("/addPayment/{studentName}").hasRole("Instructor")
                                .requestMatchers("/student/add").hasRole("Instructor")
                                .requestMatchers("/student/changeEmail/**").hasRole("Instructor")
                                .requestMatchers("/course/all").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))
                .build();

    }
}