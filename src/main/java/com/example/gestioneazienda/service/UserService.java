package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.mapper.UserMapper;
import com.example.gestioneazienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));
    }

    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(long id) {
        return this.userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Utente con id " + id + " non trovato"));
    }
}
