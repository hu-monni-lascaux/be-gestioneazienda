package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.ServiceHour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceHourMapper {

    ServiceHourDTO serviceHourToServiceHourDTO(ServiceHour serviceHour);

    ServiceHour serviceHourDTOToServiceHour(ServiceHourDTO serviceHourDTO);

    List<ServiceHourDTO> serviceHoursToServiceHourDTOs(List<ServiceHour> serviceHours);

    List<ServiceHour> serviceHourDTOsToServiceHours(List<ServiceHourDTO> serviceHourDTOs);
}
