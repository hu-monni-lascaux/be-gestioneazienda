package com.example.gestioneazienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private String title;
    private String text;
    private LocalDateTime start;
    private LocalDateTime end;
    private String username;
    private AgendaDTO agendas;
}
