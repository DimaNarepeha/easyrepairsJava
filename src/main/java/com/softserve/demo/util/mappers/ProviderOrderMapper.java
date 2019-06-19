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
    ProviderOrderDTO ProviderToProviderOrderDTO(Provider provider);

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "services", source = "serviceDTOs")
    Provider ProviderToProviderOrderDTO(ProviderOrderDTO providerDTO);

    List<ProviderOrderDTO> ProvidersToProviderOrderDTOs(List<Provider> providers);

    List<Provider> ProviderOrderDTOsToProviders(List<ProviderOrderDTO> providerDTOs);
}
