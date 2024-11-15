package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.entity.Role;
import com.example.gestioneazienda.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(source = "role", target = "authorities")
    public abstract UserDTO toUserDTO(User user);

    @Mapping(source = "authorities", target = "role")
    public abstract User toUser(UserDTO userDTO);

    protected List<String> toAuthorities(Role role) {
        return role != null ? List.of(role.name()) : List.of();
    }

    protected Role toRole(Collection<? extends GrantedAuthority> authorities) {
        if (authorities.isEmpty()) {
            return Role.BASE;
        }
        if ("ADMIN".equalsIgnoreCase(authorities.stream().findAny().get().getAuthority())) {
            return Role.ADMIN;
        }
        return Role.BASE;
    }
}
