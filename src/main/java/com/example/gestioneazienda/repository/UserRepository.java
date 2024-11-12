package com.example.gestioneazienda.repository;

import com.example.gestioneazienda.entity.Role;
import com.example.gestioneazienda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :username, u.email = :email, u.password = :password, u.role = :role WHERE u.id = :id")
    void update(long id, String username, String email, String password, Role role);
}
