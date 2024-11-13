package com.example.gestioneazienda.repository;

import com.example.gestioneazienda.entity.ServiceHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceHourRepository extends JpaRepository<ServiceHour, Long> {

    @Query("""
            select sh from ServiceHour sh
            where sh.agenda.id = :id
            """)
    List<ServiceHour> findAllByAgendaID(@Param("id") long id);
}
