package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorizationUserMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "userLogin", source = "username"),
            @Mapping(target = "roles", source = "roles"),
            @Mapping(target = "image", source = "image")

    })
    AuthorizationUserDTO userToAuthorizationUserDTO(User user);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "userLogin"),
            @Mapping(target = "roles", source = "roles"),
            @Mapping(target = "image", source = "image")
    })
    User AuthorizationUserDTOToUser(AuthorizationUserDTO authorizationUserDTO);
}
