package com.example.gestioneazienda.repository;

import com.example.gestioneazienda.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
            select a from Appointment
            where a.agenda.id = :agendaID
            """)
    List<Appointment> findAllByAgendaID(long agendaID);
}
