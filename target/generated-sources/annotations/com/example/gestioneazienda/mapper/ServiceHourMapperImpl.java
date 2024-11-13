package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.ServiceHour;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T14:06:21+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ServiceHourMapperImpl extends ServiceHourMapper {

    @Override
    public ServiceHourDTO toServiceHourDTO(ServiceHour serviceHour) {
        if ( serviceHour == null ) {
            return null;
        }

        ServiceHourDTO.ServiceHourDTOBuilder serviceHourDTO = ServiceHourDTO.builder();

        serviceHourDTO.agendaID( serviceHourAgendaId( serviceHour ) );
        serviceHourDTO.day( serviceHour.getDay() );
        serviceHourDTO.start( serviceHour.getStart() );
        serviceHourDTO.end( serviceHour.getEnd() );

        return serviceHourDTO.build();
    }

    @Override
    public ServiceHour toServiceHour(ServiceHourDTO serviceHourDTO) {
        if ( serviceHourDTO == null ) {
            return null;
        }

        ServiceHour.ServiceHourBuilder serviceHour = ServiceHour.builder();

        serviceHour.agenda( idToAgenda( serviceHourDTO.getAgendaID() ) );
        serviceHour.day( serviceHourDTO.getDay() );
        serviceHour.start( serviceHourDTO.getStart() );
        serviceHour.end( serviceHourDTO.getEnd() );

        return serviceHour.build();
    }

    @Override
    public List<ServiceHourDTO> toServiceHourDTOList(List<ServiceHour> serviceHours) {
        if ( serviceHours == null ) {
            return null;
        }

        List<ServiceHourDTO> list = new ArrayList<ServiceHourDTO>( serviceHours.size() );
        for ( ServiceHour serviceHour : serviceHours ) {
            list.add( toServiceHourDTO( serviceHour ) );
        }

        return list;
    }

    @Override
    public List<ServiceHour> toServiceHourList(List<ServiceHourDTO> serviceHourDTOs) {
        if ( serviceHourDTOs == null ) {
            return null;
        }

        List<ServiceHour> list = new ArrayList<ServiceHour>( serviceHourDTOs.size() );
        for ( ServiceHourDTO serviceHourDTO : serviceHourDTOs ) {
            list.add( toServiceHour( serviceHourDTO ) );
        }

        return list;
    }

    private long serviceHourAgendaId(ServiceHour serviceHour) {
        Agenda agenda = serviceHour.getAgenda();
        if ( agenda == null ) {
            return 0L;
        }
        return agenda.getId();
    }
}
