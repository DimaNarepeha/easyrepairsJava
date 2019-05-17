package com.softserve.demo.util;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "region", source = "region"),
            @Mapping(target = "city", source = "city"),
    })
    LocationDTO LocationToLocationDTO(Location location);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "region", source = "region"),
            @Mapping(target = "city", source = "city"),
    })
    Location LocationDTOToLocation(LocationDTO locationDTO);
}
