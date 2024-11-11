package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.AgendaDto;
import com.example.gestioneazienda.dto.ServiceHourDto;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.ServiceHour;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.repository.AgendaRepository;
import com.example.gestioneazienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgendaService {
    private AgendaRepository agendaRepository;
    private UserRepository userRepository;

    public void create(AgendaDto agendaDto) {
        User user = userRepository.findByUsername(agendaDto.getUsername())
                .orElseThrow();
        Agenda agenda = agendaRepository.save(mapDtoToAgenda(agendaDto, user));
        user.getAgendas().add(agenda);
        userRepository.save(user);
    }

    public Agenda mapDtoToAgenda(AgendaDto agendaDto, User user) {
        Agenda agenda = new Agenda();
        agenda.setAppointments(List.of());
        agenda.setName(agendaDto.getName());
        agenda.setUser(user);
        agenda.setMaxAppointmentTime(agendaDto.getMaxAppointmentTime());

        List<ServiceHour> serviceHours = new ArrayList<>();
        for(ServiceHourDto serviceHourDto : agendaDto.getServiceHoursDto()) {
            ServiceHour serviceHour = new ServiceHour();
            serviceHour.setAgenda(agenda);
            serviceHour.setDay(serviceHourDto.getDay());
            serviceHour.setStart(serviceHourDto.getStart());
            serviceHour.setEnd(serviceHourDto.getEnd());

            serviceHours.add(serviceHour);
        }
        agenda.setServiceHours(serviceHours);

        return agenda;
    }

    public AgendaDto mapAgendaToDto(Agenda agenda) {
        AgendaDto agendaDto = new AgendaDto();

        List<ServiceHourDto> serviceHoursDto = new ArrayList<>();
        for(ServiceHour serviceHour : agenda.getServiceHours()) {
            ServiceHourDto serviceHourDto = new ServiceHourDto();
            serviceHourDto.setDay(serviceHour.getDay());
            serviceHourDto.setStart(serviceHour.getStart());
            serviceHourDto.setEnd(serviceHour.getEnd());

            serviceHoursDto.add(serviceHourDto);
        }
        agendaDto.setServiceHoursDto(serviceHoursDto);

        agendaDto.setUsername(agenda.getUser().getUsername());
        agendaDto.setName(agenda.getName());
        agendaDto.setMaxAppointmentTime(agenda.getMaxAppointmentTime());
        return agendaDto;
    }

    public List<AgendaDto> getAll() {
        return agendaRepository.findAll().stream()
                .map(this::mapAgendaToDto)
                .collect(Collectors.toList());
    }

    public List<AgendaDto> getAllByUsername(String username) {
        return agendaRepository.findByUserUsername(username).stream()
                .map(this::mapAgendaToDto)
                .collect(Collectors.toList());
    }
}
