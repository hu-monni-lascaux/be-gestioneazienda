package com.example.gestioneazienda.service;

import com.example.gestioneazienda.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaService {
    private AgendaRepository agendaRepository;

    public void create(long userId) {

    }
}
