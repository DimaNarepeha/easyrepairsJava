package com.softserve.demo.util;

import com.softserve.demo.dto.UserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
