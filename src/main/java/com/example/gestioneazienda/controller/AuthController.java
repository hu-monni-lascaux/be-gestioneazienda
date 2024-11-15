package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.AuthenticationRequest;
import com.example.gestioneazienda.dto.AuthenticationResponse;
import com.example.gestioneazienda.dto.RegisterRequest;
import com.example.gestioneazienda.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(authService.authenticate(authenticationRequest), HttpStatus.OK);
    }
}
