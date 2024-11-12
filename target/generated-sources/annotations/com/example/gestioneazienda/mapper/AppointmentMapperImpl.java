package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.AppointmentDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.Appointment;
import com.example.gestioneazienda.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T17:13:56+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class AppointmentMapperImpl extends AppointmentMapper {

    @Override
    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO.AppointmentDTOBuilder appointmentDTO = AppointmentDTO.builder();

        appointmentDTO.username( appointmentUserUsername( appointment ) );
        appointmentDTO.agendaID( appointmentAgendaId( appointment ) );
        appointmentDTO.title( appointment.getTitle() );
        appointmentDTO.text( appointment.getText() );
        appointmentDTO.start( appointment.getStart() );
        appointmentDTO.end( appointment.getEnd() );

        return appointmentDTO.build();
    }

    @Override
    public Appointment toAppointment(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointment = Appointment.builder();

        if ( appointmentDTO.getAgendaID() != null ) {
            appointment.agenda( idToAgenda( appointmentDTO.getAgendaID().longValue() ) );
        }
        appointment.user( mapUsernameToUser( appointmentDTO.getUsername() ) );
        appointment.title( appointmentDTO.getTitle() );
        appointment.text( appointmentDTO.getText() );
        appointment.start( appointmentDTO.getStart() );
        appointment.end( appointmentDTO.getEnd() );

        return appointment.build();
    }

    private String appointmentUserUsername(Appointment appointment) {
        User user = appointment.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }

    private Long appointmentAgendaId(Appointment appointment) {
        Agenda agenda = appointment.getAgenda();
        if ( agenda == null ) {
            return null;
        }
        return agenda.getId();
    }
}
