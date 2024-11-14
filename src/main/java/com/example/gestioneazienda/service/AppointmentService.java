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
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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

    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        validateAppointment(appointmentDTO);
        return appointmentMapper.toAppointmentDTO(appointmentRepository.save(appointmentMapper.toAppointment(appointmentDTO)));
    }

    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        validateAppointment(appointmentDTO);
        return appointmentMapper.toAppointmentDTO(appointmentRepository.save(appointmentMapper.toAppointment(appointmentDTO)));
    }

    @Transactional(readOnly = true)
    private void validateAppointment(AppointmentDTO appointmentDTO) {
        Agenda agenda = agendaRepository.findById(appointmentDTO.getAgendaID())
                .orElseThrow(() -> new RecordNotFoundException("Agenda not found. Id " + appointmentDTO.getAgendaID()));

        Duration timeDifference = Duration.between(appointmentDTO.getStart(), appointmentDTO.getEnd());
        if(timeDifference.toMillis() < 0) {
            throw new InvalidAppointmentTimeException("The time difference (start-end) exceeds the allowed duration.");
        }

        boolean valid = false;
        for (ServiceHour serviceHour : agenda.getServiceHours()) {
            if ((appointmentDTO.getStart().isAfter(serviceHour.getStart()) || appointmentDTO.getStart().equals(serviceHour.getStart())) &&
                    (appointmentDTO.getEnd().isBefore(serviceHour.getEnd()) || appointmentDTO.getEnd().equals(serviceHour.getEnd()))) {
                valid = true;
            }
        }
        if(!valid) {
            throw new InvalidAppointmentTimeException("Appointment time is outside of available service hours.");
        }
        for (Appointment existingAppointment : agenda.getAppointments()) {
            if ((appointmentDTO.getStart().isBefore(existingAppointment.getEnd()) && appointmentDTO.getEnd().isAfter(existingAppointment.getStart()))) {
                throw new AppointmentConflictException("Appointment time conflicts with an existing appointment.");
            }
        }
    }

    public void delete(long id) {
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentDTO> getAllByAgendaID(long agendaID) {
        return appointmentRepository.findAllByAgendaID(agendaID).stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }
}
