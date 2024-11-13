package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.repository.AgendaRepository;
import com.example.gestioneazienda.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;
    private AgendaRepository agendaRepository;

    @GetMapping("/")
    public ResponseEntity<List<AppointmentDTO>> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> get(@PathVariable long id) {
        return new ResponseEntity<>(appointmentService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AppointmentDTO appointmentDTO) {
        Agenda agenda = agendaRepository.findById((long)3)
                .get();
        //appointmentService.create(appointmentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.update(appointmentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
