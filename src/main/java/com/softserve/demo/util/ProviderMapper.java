package com.softserve.demo.util;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "lastUpdate", source = "lastUpdate"),
            @Mapping(target = "userDTO", source = "user"),
//            @Mapping(target = "ordersDTO", source = "orders"),
//            @Mapping(target = "servicesDTO", source = "services"),
//            @Mapping(target = "locationDTO", source = "location")
            //TODO: create mappping for orders, services, locationsgit status
    })
    ProviderDTO ProviderToProviderDTO(Provider provider);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "lastUpdate", source = "lastUpdate"),
            @Mapping(target = "user", source = "userDTO"),
//            @Mapping(target = "orders", source = "ordersDTO"),
//            @Mapping(target = "services", source = "servicesDTO"),
//            @Mapping(target = "location", source = "locationDTO")
            //TODO: create mappping for orders, services, locations
    })
    Provider ProviderDTOToProvider(ProviderDTO providerDTO);
}
