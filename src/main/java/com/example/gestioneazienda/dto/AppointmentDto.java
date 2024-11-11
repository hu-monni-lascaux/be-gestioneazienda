package com.example.gestioneazienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private String title;
    private String text;
    private LocalDateTime start;
    private LocalDateTime end;
    private String username;
    private List<AgendaDto> agendas;
}
