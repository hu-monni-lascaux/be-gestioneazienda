package com.example.gestioneazienda.repository;

import com.example.gestioneazienda.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Query(value = """
            select a from Agenda a
            where a.user.username = :username
            """)
    List<Agenda> findByUserUsername(String username);
}
