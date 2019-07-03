package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.ProviderOrderDTO;
import com.softserve.demo.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderOrderMapper {

    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "serviceDTOs", source = "services")
    ProviderOrderDTO providerToProviderOrderDTO(Provider provider);

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "services", source = "serviceDTOs")
    Provider providerOrderDTOToProvider(ProviderOrderDTO providerDTO);

    List<ProviderOrderDTO> providersToProviderOrderDTOs(List<Provider> providers);

    List<Provider> providerOrderDTOsToProviders(List<ProviderOrderDTO> providerDTOs);
}
