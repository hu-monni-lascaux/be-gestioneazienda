package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.service.ServiceHourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/service-hour")
public class ServiceHourController {
    private ServiceHourService serviceHourService;

    @GetMapping
    public ResponseEntity<List<ServiceHourDTO>> all() {
        return new ResponseEntity<>(serviceHourService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceHourDTO> id(@PathVariable long id) {
        return new ResponseEntity<>(serviceHourService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/agenda/{agendaID}")
    public ResponseEntity<List<ServiceHourDTO>> allByAgendaID(@PathVariable long agendaID) {
        return new ResponseEntity<>(serviceHourService.getAllByAgendaID(agendaID), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServiceHourDTO serviceHourDTO) {
        return new ResponseEntity<>(serviceHourService.create(serviceHourDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ServiceHourDTO serviceHourDTO) {
        serviceHourService.update(serviceHourDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        serviceHourService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
