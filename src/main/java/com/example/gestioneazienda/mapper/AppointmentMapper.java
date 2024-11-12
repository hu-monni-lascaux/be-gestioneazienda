package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.Appointment;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.repository.AgendaRepository;
import com.example.gestioneazienda.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Mapper(componentModel = "spring")
public abstract class AppointmentMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    @Mapping(target = "username", source = "appointment.user.username")
    @Mapping(target = "agendaID", source = "appointment.agenda.id")
    public abstract AppointmentDTO toAppointmentDTO(Appointment appointment);

    @Mapping(target = "agenda", source = "agendaID", qualifiedByName = "idToAgenda")
    @Mapping(target = "user", source = "username", qualifiedByName = "usernameToUser")
    public abstract Appointment toAppointment(AppointmentDTO appointmentDTO);

    @Named("usernameToUser")
    protected User mapUsernameToUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " username non trovato"));
    }

    @Named("idToAgenda")
    protected Agenda idToAgenda(long agendaID) {
        return agendaRepository.findById(agendaID)
                .orElseThrow(() -> new RuntimeException(agendaID + " id (agenda) non trovato"));
    }
}
