package com.example.gestioneazienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaDTO {
    private String name;
    private String username;
    private Duration maxAppointmentTime;
    private List<ServiceHourDTO> serviceHoursDTO;
}
