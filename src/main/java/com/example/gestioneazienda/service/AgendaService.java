package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.AgendaDTO;
import com.example.gestioneazienda.entity.Agenda;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.mapper.AgendaMapper;
import com.example.gestioneazienda.repository.AgendaRepository;
import com.example.gestioneazienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgendaService {
    private AgendaRepository agendaRepository;
    private UserRepository userRepository;
    private AgendaMapper agendaMapper;

    public AgendaDTO create(AgendaDTO agendaDTO) {
        User user = userRepository.findByUsername(agendaDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for username: " + agendaDTO.getUsername()));
        Agenda agendaOLD = agendaMapper.toAgenda(agendaDTO);
        Agenda agenda = agendaRepository.save(agendaOLD);
        user.getAgendas().add(agenda);
        return agendaMapper.toAgendaDTO(agenda);
    }

    public List<AgendaDTO> getAll() {
        return agendaRepository.findAll().stream()
                .map(agendaMapper::toAgendaDTO)
                .collect(Collectors.toList());
    }

    public List<AgendaDTO> getAllByUsername(String username) {
        return agendaRepository.findByUserUsername(username).stream()
                .map(agendaMapper::toAgendaDTO)
                .collect(Collectors.toList());
    }

    public AgendaDTO update(AgendaDTO agendaDTO) {
        Agenda agenda = agendaMapper.toAgenda(agendaDTO);
        return agendaMapper.toAgendaDTO(agendaRepository.save(agenda));
    }

    public void delete(long id) {
        agendaRepository.deleteById(id);
    }
}
