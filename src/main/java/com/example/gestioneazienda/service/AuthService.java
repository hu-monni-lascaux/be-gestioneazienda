package com.example.gestioneazienda.service;

import com.example.gestioneazienda.config.JwtService;
import com.example.gestioneazienda.dto.AuthenticationRequest;
import com.example.gestioneazienda.dto.AuthenticationResponse;
import com.example.gestioneazienda.dto.RegisterRequest;
import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.entity.Role;
import com.example.gestioneazienda.entity.Token;
import com.example.gestioneazienda.entity.TokenType;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.mapper.UserMapper;
import com.example.gestioneazienda.repository.TokenRepository;
import com.example.gestioneazienda.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var userDto = userMapper.toUserDTO(user);
        var jwtToken = jwtService.generateToken(userDto);
        //var refreshToken = jwtService.generateRefreshToken(userDto);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            UserDTO userDto = userMapper.toUserDTO(user);
            if (jwtService.isTokenValid(refreshToken, userDto)) {
                var jwtToken = jwtService.generateToken(userDto);
                revokeAllUserTokens(user);
                saveUserToken(user, jwtToken);
                var authResponse = AuthenticationResponse.builder()
                        .jwtToken(jwtToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.BASE)
                .build();
        var savedUser = userRepository.save(user);
        var userDto = userMapper.toUserDTO(user);
        var jwtToken = jwtService.generateToken(userDto);
        //var refreshToken = jwtService.generateRefreshToken(userDto);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public void verifyToken(String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring("Bearer ".length()));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " username not found"));
        UserDTO userDTO = userMapper.toUserDTO(user);
        Collection<? extends GrantedAuthority> authorities = userDTO.getAuthorities();

        Authentication authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
