package com.example.gestioneazienda.service;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.entity.User;
import com.example.gestioneazienda.exception.RecordNotFoundException;
import com.example.gestioneazienda.mapper.UserMapper;
import com.example.gestioneazienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));
    }

    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(long id) {
        return this.userRepository.findById(id)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new RecordNotFoundException("User not found. Id = " + id));
    }

    public UserDTO getByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("User not found. Username = " + username));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void update(UserDTO userDTO) {
        User userOLD = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDTO.getUsername() + " username non trovato"));
        User userNEW = userMapper.toUser(userDTO);
        userNEW.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.update(userOLD.getId(), userNEW.getUsername(), userNEW.getEmail(), userNEW.getPassword(), userNEW.getRole());
    }


}
