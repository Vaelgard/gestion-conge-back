package com.project.gestionpersonnelback.mappers;

import com.project.gestionpersonnelback.dtos.UserDto;
import com.project.gestionpersonnelback.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
