package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.ServiceHour;
import com.example.gestioneazienda.exception.RecordNotFoundException;
import com.example.gestioneazienda.repository.AgendaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ServiceHourMapper {
    @Autowired
    private AgendaRepository agendaRepository;

    @Mapping(target = "agendaID", source = "serviceHour.agenda.id")
    public abstract ServiceHourDTO toServiceHourDTO(ServiceHour serviceHour);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agenda", source = "agendaID", qualifiedByName = "idToAgenda")
    public abstract ServiceHour toServiceHour(ServiceHourDTO serviceHourDTO);

    public abstract List<ServiceHourDTO> toServiceHourDTOList(List<ServiceHour> serviceHours);

    public abstract List<ServiceHour> toServiceHourList(List<ServiceHourDTO> serviceHourDTOs);

    @Named("idToAgenda")
    protected Agenda idToAgenda(long agendaID) {
        return agendaRepository.findById(agendaID)
                .orElseThrow(() -> new RecordNotFoundException("Agenda not found. Id = " + agendaID));
    }
}
