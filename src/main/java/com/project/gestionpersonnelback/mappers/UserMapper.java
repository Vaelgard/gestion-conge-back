package com.project.gestionpersonnelback.mappers;


import com.project.gestionpersonnelback.dtos.UserDTO;
import com.project.gestionpersonnelback.entities.OurUsers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(OurUsers user);
    OurUsers userDTOToUser(UserDTO userDTO);
}
