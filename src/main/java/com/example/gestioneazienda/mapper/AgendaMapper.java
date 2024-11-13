package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AgendaDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
                ServiceHourMapper.class,
                AppointmentMapper.class
        }
)
public abstract class AgendaMapper {
    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "username", qualifiedByName = "usernameToUser")
    public abstract Agenda toAgenda(AgendaDTO agendaDTO);

    @Mapping(target = "username", source = "agenda.user.username")
    public abstract AgendaDTO toAgendaDTO(Agenda agenda);

}
