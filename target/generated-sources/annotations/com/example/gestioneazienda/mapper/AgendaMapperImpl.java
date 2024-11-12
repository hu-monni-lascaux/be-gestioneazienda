package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AgendaDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T10:20:09+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class AgendaMapperImpl extends AgendaMapper {

    @Autowired
    private ServiceHourMapper serviceHourMapper;

    @Override
    public Agenda agendaDTOtoAgenda(AgendaDTO agendaDTO) {
        if ( agendaDTO == null ) {
            return null;
        }

        Agenda.AgendaBuilder agenda = Agenda.builder();

        agenda.user( mapUsernameToUser( agendaDTO.getUsername() ) );
        agenda.serviceHours( serviceHourMapper.serviceHourDTOsToServiceHours( agendaDTO.getServiceHoursDTO() ) );
        agenda.name( agendaDTO.getName() );
        agenda.maxAppointmentTime( agendaDTO.getMaxAppointmentTime() );

        return agenda.build();
    }

    @Override
    public AgendaDTO agendaToAgendaDTO(Agenda agenda) {
        if ( agenda == null ) {
            return null;
        }

        AgendaDTO.AgendaDTOBuilder agendaDTO = AgendaDTO.builder();

        agendaDTO.username( agendaUserUsername( agenda ) );
        agendaDTO.serviceHoursDTO( serviceHourMapper.serviceHoursToServiceHourDTOs( agenda.getServiceHours() ) );
        agendaDTO.name( agenda.getName() );
        agendaDTO.maxAppointmentTime( agenda.getMaxAppointmentTime() );

        return agendaDTO.build();
    }

    private String agendaUserUsername(Agenda agenda) {
        User user = agenda.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }
}
