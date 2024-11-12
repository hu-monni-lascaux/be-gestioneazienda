package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.entity.Role;
import com.example.gestioneazienda.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "authorities")
    UserDTO userToUserDTO(User user);

    default List<GrantedAuthority> mapRoleToAuthorities(Role role) {
        return role != null ? List.of(new SimpleGrantedAuthority(role.name())) : List.of();
    }
}
