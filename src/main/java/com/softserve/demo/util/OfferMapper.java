package com.softserve.demo.util;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.model.Offer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface OfferMapper {

    OfferDTO OfferToOfferDTO(Offer offer);

    Offer OfferDTOToOffer(OfferDTO offerDTO);

    List<OfferDTO> OffersToOfferDTOs(List<Offer> offers);

    List<Offer> OfferDTOsToOffer(List<OfferDTO> offers);
}
