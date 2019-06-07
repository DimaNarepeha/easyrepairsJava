package com.softserve.demo.util;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mappings({
            @Mapping(target = "userDTO", source = "user"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "image", source = "user.image"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "countComment", expression = "java( provider.getUser().getFeedbackTo().size())"),
            @Mapping(target = "services", expression = "java(provider.getServices().stream().map(urEntity -> urEntity.getServiceName()).collect(java.util.stream.Collectors.toList()))")
    })
    ProviderDTO providerToProviderDTO(Provider provider);

    @Mappings({
            @Mapping(target = "user", source = "userDTO"),
            @Mapping(target = "user.email", source = "email"),
            @Mapping(target = "user.image", source = "image"),
            @Mapping(target = "services", ignore = true)
    })
    Provider providerDTOToProvider(ProviderDTO providerDTO);

    @Mappings({
            @Mapping(target = "id", source = "userDTO.id"),
            @Mapping(target = "username", source = "userDTO.username"),
            @Mapping(target = "password", source = "userDTO.password"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "image", source = "image")
    })
    User providerDTOToUser(ProviderDTO providerDTO);
}
