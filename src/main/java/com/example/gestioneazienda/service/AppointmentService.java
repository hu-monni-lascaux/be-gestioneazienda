package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.Appointment;
import com.example.gestioneazienda.entity.ServiceHour;
import com.example.gestioneazienda.exception.AppointmentConflictException;
import com.example.gestioneazienda.exception.InvalidAppointmentTimeException;
import com.example.gestioneazienda.exception.RecordNotFoundException;
import com.example.gestioneazienda.mapper.AppointmentMapper;
import com.example.gestioneazienda.repository.AgendaRepository;
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
    private AgendaRepository agendaRepository;

    public List<AppointmentDTO> getAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO getById(long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toAppointmentDTO)
                .orElseThrow(() -> new RecordNotFoundException("Appointment not found. Id " + id));
    }

    public void create(AppointmentDTO appointmentDTO) {
        validateAppointment(appointmentDTO);
        appointmentRepository.save(appointmentMapper.toAppointment(appointmentDTO));
    }

    public void update(AppointmentDTO appointmentDTO) {
        validateAppointment(appointmentDTO);
        appointmentRepository.save(appointmentMapper.toAppointment(appointmentDTO));
    }

    private void validateAppointment(AppointmentDTO appointmentDTO) {
        Agenda agenda = agendaRepository.findById(appointmentDTO.getAgendaID())
                .orElseThrow(() -> new RecordNotFoundException("Agenda not found. Id " + appointmentDTO.getAgendaID()));

        boolean valid = false;
        for (ServiceHour serviceHour : agenda.getServiceHours()) {
            if ((appointmentDTO.getStart().isAfter(serviceHour.getStart()) || appointmentDTO.getStart().isEqual(serviceHour.getStart())) &&
                    (appointmentDTO.getEnd().isBefore(serviceHour.getEnd()) || appointmentDTO.getEnd().isEqual(serviceHour.getEnd()))) {
                valid = true;
            }
        }
        // TODO da finire validare gli appuntamenti
        if(!valid) {
            throw new InvalidAppointmentTimeException("Appointment time is outside of available service hours. ")
        }
        for (Appointment existingAppointment : agenda.getAppointments()) {
            // Check if the new appointment's time range overlaps with any existing appointment
            if ((appointmentDTO.getStart().isBefore(existingAppointment.getEnd()) && appointmentDTO.getEnd().isAfter(existingAppointment.getStart()))) {
                throw new AppointmentConflictException("Appointment time conflicts with an existing appointment.");
            }
        }
    }

    public void delete(long id) {
        appointmentRepository.deleteById(id);
    }
}
