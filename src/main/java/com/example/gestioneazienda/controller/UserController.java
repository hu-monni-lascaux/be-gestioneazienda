package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.UserDto;
import com.example.gestioneazienda.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> all() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
