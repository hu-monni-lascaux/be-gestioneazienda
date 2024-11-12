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
    date = "2024-11-12T17:12:17+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class AgendaMapperImpl extends AgendaMapper {

    @Autowired
    private ServiceHourMapper serviceHourMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public Agenda toAgenda(AgendaDTO agendaDTO) {
        if ( agendaDTO == null ) {
            return null;
        }

        Agenda.AgendaBuilder agenda = Agenda.builder();

        agenda.user( mapUsernameToUser( agendaDTO.getUsername() ) );
        agenda.serviceHours( serviceHourMapper.toServiceHourList( agendaDTO.getServiceHoursDTO() ) );
        agenda.appointments( appointmentDTOListToAppointmentList( agendaDTO.getAppointmentsDTO() ) );
        agenda.name( agendaDTO.getName() );
        agenda.maxAppointmentTime( agendaDTO.getMaxAppointmentTime() );

        return agenda.build();
    }

    @Override
    public AgendaDTO toAgendaDTO(Agenda agenda) {
        if ( agenda == null ) {
            return null;
        }

        AgendaDTO.AgendaDTOBuilder agendaDTO = AgendaDTO.builder();

        agendaDTO.username( agendaUserUsername( agenda ) );
        agendaDTO.serviceHoursDTO( serviceHourMapper.toServiceHourDTOList( agenda.getServiceHours() ) );
        agendaDTO.appointmentsDTO( appointmentListToAppointmentDTOList( agenda.getAppointments() ) );
        agendaDTO.name( agenda.getName() );
        agendaDTO.maxAppointmentTime( agenda.getMaxAppointmentTime() );

        return agendaDTO.build();
    }

    protected List<Appointment> appointmentDTOListToAppointmentList(List<AppointmentDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Appointment> list1 = new ArrayList<Appointment>( list.size() );
        for ( AppointmentDTO appointmentDTO : list ) {
            list1.add( appointmentMapper.toAppointment( appointmentDTO ) );
        }

        return list1;
    }

    private String agendaUserUsername(Agenda agenda) {
        User user = agenda.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }

    protected List<AppointmentDTO> appointmentListToAppointmentDTOList(List<Appointment> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDTO> list1 = new ArrayList<AppointmentDTO>( list.size() );
        for ( Appointment appointment : list ) {
            list1.add( appointmentMapper.toAppointmentDTO( appointment ) );
        }

        return list1;
    }
}
