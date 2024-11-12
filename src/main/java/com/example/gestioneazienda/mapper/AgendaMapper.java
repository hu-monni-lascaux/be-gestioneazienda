package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AgendaDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
                ServiceHourMapper.class
        }
)
public abstract class AgendaMapper {
    @Autowired
    private UserService userService;

    @Mapping(target = "user", source = "username", qualifiedByName = "usernameToUser")
    @Mapping(target = "serviceHours", source = "serviceHoursDTO")
    @Mapping(target = "appointments", source = "appointmentsDTO")
    public abstract Agenda toAgenda(AgendaDTO agendaDTO);

    @Mapping(target = "username", source = "agenda.user.username")
    @Mapping(target = "serviceHoursDTO", source = "serviceHours")
    @Mapping(target = "appointmentsDTO", source = "appointments")
    public abstract AgendaDTO toAgendaDTO(Agenda agenda);

    @Named("usernameToUser")
    protected User mapUsernameToUser(String username) {
        return (User) userService.loadUserByUsername(username);
    }

}
