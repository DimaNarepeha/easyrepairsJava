package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.UserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
