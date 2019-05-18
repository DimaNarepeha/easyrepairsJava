package com.softserve.demo.util;

import com.softserve.demo.dto.ServiceProviderInfoDTO;
import com.softserve.demo.model.Provider;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = spring)
public interface ServiceProviderMapper {

   // ServiceProviderMapper INSTANCE = Mappers.getMapper(ServiceProviderMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "name", source = "entity.name"),
            @Mapping(target = "city", source = "entity.location.city"),
            @Mapping(target = "image", source = "entity.image"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "registrationDate", source = "entity.lastUpdate"),
            @Mapping(target = "services", expression = "java(entity.getServices().stream().map(urEntity -> urEntity.getServiceName()).collect(java.util.stream.Collectors.toList()))"),
            @Mapping(target = "raiting", source = "entity.raiting"),
            @Mapping(target = "countComment", expression = "java( entity.getUser().getFeedbackTo().size())")
    })
    @Named("toDto")
    abstract ServiceProviderInfoDTO convertToDTO(Provider entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "location.city", source = "dto.city"),
            @Mapping(target = "image", source = "dto.image"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "lastUpdate", source = "dto.registrationDate"),
            @Mapping(target = "services", ignore = true),
            @Mapping(target = "raiting", source = "dto.raiting")
    })
    Provider converToEntity(ServiceProviderInfoDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    abstract List<ServiceProviderInfoDTO> map(List<Provider> children);
}


