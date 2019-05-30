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
            @Mapping(target = "userDTO", source = "user"),
//            @Mapping(target = "ordersDTO", source = "orders"),
//            @Mapping(target = "servicesDTO", source = "services"),
            @Mapping(target = "location", source = "location")
            //TODO: create mapping for orders, services, locations
    })
    ProviderDTO providerToProviderDTO(Provider provider);

    @Mappings({
            @Mapping(target = "user", source = "userDTO"),
//            @Mapping(target = "orders", source = "ordersDTO"),
//            @Mapping(target = "services", source = "servicesDTO"),
            //TODO: create mapping for orders, services
    })
    Provider providerDTOToProvider(ProviderDTO providerDTO);
}
