package com.softserve.demo.util;

import com.softserve.demo.dto.ProviderInfoDTO;
import com.softserve.demo.model.Provider;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderInfoMapper {
            @Mapping(target = "id", source = "entity.id")
            @Mapping(target = "name", source = "entity.name")
            @Mapping(target = "city", source = "entity.location.city")
            @Mapping(target = "image", source = "entity.user.image")
            @Mapping(target = "description", source = "entity.description")
            @Mapping(target = "registrationDate", source = "entity.registrationDate")
            @Mapping(target = "services", expression = "java(entity.getServices().stream().map(urEntity -> urEntity.getServiceName()).collect(java.util.stream.Collectors.toList()))")
            @Mapping(target = "raiting", source = "entity.raiting")
            @Mapping(target = "countComment", expression = "java( entity.getUser().getFeedbackTo().size())")
            @Mapping(target = "countContract", expression = "java( entity.getOrders().size())")
    @Named("toDto")
    ProviderInfoDTO convertToDTO(Provider entity);

            @Mapping(target = "id", source = "dto.id")
            @Mapping(target = "name", source = "dto.name")
            @Mapping(target = "location.city", source = "dto.city")
            @Mapping(target = "image", source = "dto.image")
            @Mapping(target = "description", source = "dto.description")
            @Mapping(target = "registrationDate", source = "dto.registrationDate")
            @Mapping(target = "services", ignore = true)
            @Mapping(target = "raiting", source = "dto.raiting")
    Provider convertToEntity(ProviderInfoDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<ProviderInfoDTO> map(List<Provider> children);
}

