package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        AgendaMapper.class
})
public interface AppointmentMapper {

    @Mapping(target = "username", source = "appointment.user.username")
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);

    @Mapping(target = "user", source = "username", qualifiedByName = "usernameToUser")
    Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO);
}
