package com.example.gestioneazienda.dto;

import com.example.gestioneazienda.entity.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceHourDTO {
    private long id;
    private Day day;
    private LocalTime start;
    private LocalTime end;
    private long agendaID;
}
