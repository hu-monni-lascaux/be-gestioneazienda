package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.AgendaDto;
import com.example.gestioneazienda.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    @GetMapping("/")
    public ResponseEntity<List<AgendaDto>> getAll() {
        return new ResponseEntity<>(agendaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<AgendaDto>> getAllByUsername(@PathVariable String username) {
        return new ResponseEntity<>(agendaService.getAllByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AgendaDto agendaDto) {
        agendaService.create(agendaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
