package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable long userId) {
        agendaService.create(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
