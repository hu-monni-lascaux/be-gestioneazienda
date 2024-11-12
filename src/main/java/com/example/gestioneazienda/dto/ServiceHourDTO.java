package com.example.gestioneazienda.dto;

import com.example.gestioneazienda.entity.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceHourDTO {
    private Day day;
    private LocalDateTime start;
    private LocalDateTime end;
}
