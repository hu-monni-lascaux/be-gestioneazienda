package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.mapper.AppointmentMapper;
import com.example.gestioneazienda.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;

    public List<AppointmentDTO> getAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

}
