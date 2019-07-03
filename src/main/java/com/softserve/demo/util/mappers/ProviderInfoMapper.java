package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.ProviderInfoDTO;
import com.softserve.demo.model.Provider;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderInfoMapper {
            @Mapping(target = "id", source = "entity.id")
            @Mapping(target = "name", source = "entity.name")
            @Mapping(target = "city", source = "entity.location.city")
            @Mapping(target = "image", source = "user.image")
            @Mapping(target = "description", source = "entity.description")
            @Mapping(target = "registrationDate", source = "entity.registrationDate")
            @Mapping(target = "services", expression = "java(entity.getServices().stream().map(urEntity -> urEntity.getServiceName()).collect(java.util.stream.Collectors.toList()))")
            @Mapping(target = "raiting", source = "entity.raiting")
            @Mapping(target = "countComment", expression = "java( entity.getUser().getFeedbackTo().size())")
            @Mapping(target = "countContract", expression = "java( entity.getOrders().size())")
    @Named("toDto")
    ProviderInfoDTO convertToDTO(Provider entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ProviderInfoDTO> map(List<Provider> children);
}


