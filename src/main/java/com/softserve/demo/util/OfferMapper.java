package com.softserve.demo.util;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);
}
