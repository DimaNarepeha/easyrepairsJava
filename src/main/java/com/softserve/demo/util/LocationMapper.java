package com.softserve.demo.util;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface LocationMapper {

    LocationDTO LocationToLocationDTO(Location location);

    Location LocationDTOToLocation(LocationDTO locationDTO);
}
