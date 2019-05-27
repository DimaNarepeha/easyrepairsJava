package com.softserve.demo.util;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.model.Offer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface OfferMapper {

    OfferDTO offerToOfferDTO(Offer offer);

    Offer offerDTOToOffer(OfferDTO offerDTO);

    List<OfferDTO> offersToOfferDTOs(List<Offer> offers);

    List<Offer> offerDTOsToOffer(List<OfferDTO> offers);
}
