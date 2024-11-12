package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.AgendaDTO;
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
    public ResponseEntity<List<AgendaDTO>> getAll() {
        return new ResponseEntity<>(agendaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<AgendaDTO>> getAllByUsername(@PathVariable String username) {
        return new ResponseEntity<>(agendaService.getAllByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AgendaDTO agendaDto) {
        agendaService.create(agendaDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody AgendaDTO agendaDTO) {
        agendaService.update(agendaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        agendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
