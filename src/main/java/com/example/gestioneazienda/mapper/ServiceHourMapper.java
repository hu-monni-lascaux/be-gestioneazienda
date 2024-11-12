package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.ServiceHour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceHourMapper {

    ServiceHourDTO toServiceHourDTO(ServiceHour serviceHour);

    ServiceHour toServiceHour(ServiceHourDTO serviceHourDTO);

    List<ServiceHourDTO> toServiceHourDTOList(List<ServiceHour> serviceHours);

    List<ServiceHour> toServiceHourList(List<ServiceHourDTO> serviceHourDTOs);
}
