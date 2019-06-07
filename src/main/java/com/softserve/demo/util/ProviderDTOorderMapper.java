package com.softserve.demo.util;

import com.softserve.demo.dto.ProviderDTOorder;
import com.softserve.demo.model.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderDTOorderMapper {
    ProviderDTOorder providerToProviderDTOorder(Provider provider);
    Provider providerDTOorderToProvider(ProviderDTOorder providerDTO);
}
