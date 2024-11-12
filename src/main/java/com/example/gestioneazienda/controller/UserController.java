package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.service.AuthService;
import com.example.gestioneazienda.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        return new ResponseEntity<>((UserDTO)userService.loadUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> update(@RequestHeader("Authorization") String jwtToken, @RequestBody UserDTO userDTO) {
        authService.verifyToken(jwtToken);
        userService.update(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}