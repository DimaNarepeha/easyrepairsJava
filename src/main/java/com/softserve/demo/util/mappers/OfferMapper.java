package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "customerDTO", source = "customer")
    @Mapping(target = "customerDTO.userDTO", source = "customer.user")
    @Mapping(target = "serviceDTOs", source = "services")
    @Mapping(target = "locationDTO", source = "location")
    OfferDTO offerToOfferDTO(Offer offer);

    @Mapping(target = "customer", source = "customerDTO")
    @Mapping(target = "customer.user", source = "customerDTO.userDTO")
    @Mapping(target = "services", source = "serviceDTOs")
    @Mapping(target = "location", source = "locationDTO")
    Offer offerDTOToOffer(OfferDTO offerDTO);

    List<OfferDTO> offersToOfferDTOs(List<Offer> offers);

    List<Offer> offerDTOsToOffer(List<OfferDTO> offerDTOs);
}
