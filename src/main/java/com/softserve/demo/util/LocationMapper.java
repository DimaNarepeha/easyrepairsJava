package com.softserve.demo.util;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface LocationMapper {

    LocationDTO locationToLocationDTO(Location location);

    Location locationDTOToLocation(LocationDTO locationDTO);
}
