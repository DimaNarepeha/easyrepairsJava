package com.softserve.demo.util;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorizationUserMapper {

    @Mappings({
            @Mapping(target = "userLogin", source = "username")
    })
    AuthorizationUserDTO userToAuthorizationUserDTO(User user);
}
