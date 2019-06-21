package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.UserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
