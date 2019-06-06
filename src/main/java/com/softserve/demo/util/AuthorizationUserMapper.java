package com.softserve.demo.util;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorizationUserMapper {
    @Mapping(target = "userLogin", source = "username")
    AuthorizationUserDTO userToAuthorizationUserDTO(User user);

    @Mapping(target = "username", source = "userLogin")
    User authorizationUserDTOToUser(AuthorizationUserDTO authorizationUserDTO);
}
