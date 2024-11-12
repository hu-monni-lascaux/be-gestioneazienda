package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.ServiceHour;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T09:47:40+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ServiceHourMapperImpl implements ServiceHourMapper {

    @Override
    public ServiceHourDTO serviceHourToServiceHourDTO(ServiceHour serviceHour) {
        if ( serviceHour == null ) {
            return null;
        }

        ServiceHourDTO.ServiceHourDTOBuilder serviceHourDTO = ServiceHourDTO.builder();

        serviceHourDTO.day( serviceHour.getDay() );
        serviceHourDTO.start( serviceHour.getStart() );
        serviceHourDTO.end( serviceHour.getEnd() );

        return serviceHourDTO.build();
    }

    @Override
    public ServiceHour serviceHourDTOToServiceHour(ServiceHourDTO serviceHourDTO) {
        if ( serviceHourDTO == null ) {
            return null;
        }

        ServiceHour.ServiceHourBuilder serviceHour = ServiceHour.builder();

        serviceHour.day( serviceHourDTO.getDay() );
        serviceHour.start( serviceHourDTO.getStart() );
        serviceHour.end( serviceHourDTO.getEnd() );

        return serviceHour.build();
    }

    @Override
    public List<ServiceHourDTO> serviceHoursToServiceHourDTOs(List<ServiceHour> serviceHours) {
        if ( serviceHours == null ) {
            return null;
        }

        List<ServiceHourDTO> list = new ArrayList<ServiceHourDTO>( serviceHours.size() );
        for ( ServiceHour serviceHour : serviceHours ) {
            list.add( serviceHourToServiceHourDTO( serviceHour ) );
        }

        return list;
    }

    @Override
    public List<ServiceHour> serviceHourDTOsToServiceHours(List<ServiceHourDTO> serviceHourDTOs) {
        if ( serviceHourDTOs == null ) {
            return null;
        }

        List<ServiceHour> list = new ArrayList<ServiceHour>( serviceHourDTOs.size() );
        for ( ServiceHourDTO serviceHourDTO : serviceHourDTOs ) {
            list.add( serviceHourDTOToServiceHour( serviceHourDTO ) );
        }

        return list;
    }
}
