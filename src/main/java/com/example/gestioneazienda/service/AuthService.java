package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.LoginRequest;
import com.example.gestioneazienda.dto.LoginResponse;
import com.example.gestioneazienda.dto.UserDto;
import com.example.gestioneazienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private UserService userService;
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        UserDto userDto = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .map(userService::mapUserToUserDto)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + loginRequest.getUsername()));
        return new LoginResponse(userDto);
    }
}
