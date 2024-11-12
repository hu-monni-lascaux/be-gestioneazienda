package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AgendaDTO;
import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.Appointment;
import com.example.gestioneazienda.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T10:05:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Autowired
    private AgendaMapper agendaMapper;

    @Override
    public AppointmentDTO appointmentToAppointmentDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO.AppointmentDTOBuilder appointmentDTO = AppointmentDTO.builder();

        appointmentDTO.username( appointmentUserUsername( appointment ) );
        appointmentDTO.title( appointment.getTitle() );
        appointmentDTO.text( appointment.getText() );
        appointmentDTO.start( appointment.getStart() );
        appointmentDTO.end( appointment.getEnd() );
        appointmentDTO.agendas( agendaListToAgendaDTOList( appointment.getAgendas() ) );

        return appointmentDTO.build();
    }

    @Override
    public Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointment = Appointment.builder();

        appointment.user( agendaMapper.mapUsernameToUser( appointmentDTO.getUsername() ) );
        appointment.title( appointmentDTO.getTitle() );
        appointment.text( appointmentDTO.getText() );
        appointment.start( appointmentDTO.getStart() );
        appointment.end( appointmentDTO.getEnd() );
        appointment.agendas( agendaDTOListToAgendaList( appointmentDTO.getAgendas() ) );

        return appointment.build();
    }

    private String appointmentUserUsername(Appointment appointment) {
        User user = appointment.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }

    protected List<AgendaDTO> agendaListToAgendaDTOList(List<Agenda> list) {
        if ( list == null ) {
            return null;
        }

        List<AgendaDTO> list1 = new ArrayList<AgendaDTO>( list.size() );
        for ( Agenda agenda : list ) {
            list1.add( agendaMapper.agendaToAgendaDTO( agenda ) );
        }

        return list1;
    }

    protected List<Agenda> agendaDTOListToAgendaList(List<AgendaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Agenda> list1 = new ArrayList<Agenda>( list.size() );
        for ( AgendaDTO agendaDTO : list ) {
            list1.add( agendaMapper.agendaDTOtoAgenda( agendaDTO ) );
        }

        return list1;
    }
}
