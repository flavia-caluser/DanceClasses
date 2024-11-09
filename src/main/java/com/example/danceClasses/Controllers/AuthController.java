package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.UserRequestDTO;
import com.example.danceClasses.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        authService.registerUser(userRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/token")
    public ResponseEntity<String> getAccessToken(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok(authService.getAccessToken(username, password, false));
    }
}
