package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.AppointmentDto;
import com.example.gestioneazienda.entity.Appointment;
import com.example.gestioneazienda.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public List<AppointmentDto> getAll() {
        return appointmentRepository.findAll().stream()
                .map(this::mapAppointmentToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto mapAppointmentToDto(Appointment appointment) {
        return null; // todo da completare
    }
}
