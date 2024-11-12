package com.example.gestioneazienda.mapper;

import com.example.gestioneazienda.dto.UserDTO;
import com.example.gestioneazienda.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:43:07+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.authorities( toAuthorities( user.getRole() ) );
        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );

        return userDTO.build();
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.role( toRole( userDTO.getAuthorities() ) );
        if ( userDTO.getId() != null ) {
            user.id( userDTO.getId() );
        }
        user.username( userDTO.getUsername() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );

        return user.build();
    }
}
