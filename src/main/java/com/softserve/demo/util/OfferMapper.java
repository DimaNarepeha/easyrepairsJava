package com.softserve.demo.util;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "startDate", source = "startDate"),
            @Mapping(target = "description", source = "description"),
//            @Mapping(target = "customerDTO", source = "customer"),
            @Mapping(target = "locationDTO", source = "location"),
            @Mapping(target = "services", expression = "java(offer.getServices().stream()" +
                    ".map(x -> x.getServiceName()).collect(java.util.stream" +
                    ".Collectors.toList()))")
    })
    OfferDTO OfferToOfferDTO(Offer offer);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "startDate", source = "startDate"),
            @Mapping(target = "description", source = "description"),
//            @Mapping(target = "customer", source = "customerDTO"),
            @Mapping(target = "location", ignore = true),
            @Mapping(target="services", ignore = true)
    })
    Offer OfferDTOToOffer(OfferDTO offerDTO);
}
