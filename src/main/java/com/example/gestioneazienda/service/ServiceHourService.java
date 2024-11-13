package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.ServiceHourDTO;
import com.example.gestioneazienda.entity.ServiceHour;
import com.example.gestioneazienda.exception.RecordNotFoundException;
import com.example.gestioneazienda.mapper.ServiceHourMapper;
import com.example.gestioneazienda.repository.ServiceHourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceHourService {
    private ServiceHourRepository serviceHourRepository;
    private ServiceHourMapper serviceHourMapper;

    public List<ServiceHourDTO> getAll() {
        return serviceHourRepository.findAll().stream()
                .map(serviceHourMapper::toServiceHourDTO)
                .collect(Collectors.toList());
    }

    public ServiceHourDTO getById(long id) {
        return serviceHourRepository.findById(id)
                .map(serviceHourMapper::toServiceHourDTO)
                .orElseThrow(() -> new RecordNotFoundException("Service Hour not found. Id = " + id));
    }

    public List<ServiceHourDTO> getAllByAgendaID(long agendaID) {
        return serviceHourRepository.findAllByAgendaID(agendaID).stream()
                .map(serviceHourMapper::toServiceHourDTO)
                .collect(Collectors.toList());
    }

    public ServiceHourDTO create(ServiceHourDTO serviceHourDTO) {
        ServiceHour serviceHour = serviceHourRepository.save(serviceHourMapper.toServiceHour(serviceHourDTO));
        return serviceHourMapper.toServiceHourDTO(serviceHour);
    }

    public void deleteById(long id) {
        serviceHourRepository.deleteById(id);
    }

    public ServiceHourDTO update(ServiceHourDTO serviceHourDTO) {
        ServiceHour serviceHour = serviceHourRepository.save(serviceHourMapper.toServiceHour(serviceHourDTO));
        return serviceHourMapper.toServiceHourDTO(serviceHour);
    }
}
