package com.example.gestioneazienda.controller;

import com.example.gestioneazienda.dto.AppointmentDto;
import com.example.gestioneazienda.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    @GetMapping("/")
    public ResponseEntity<List<AppointmentDto>> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }
}
