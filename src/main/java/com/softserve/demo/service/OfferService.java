package com.softserve.demo.service;

import com.softserve.demo.model.Offer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfferService {

    void createOffer(Offer offer);

    void updateOffer(Offer offer);

    List<Offer> getAllOffers();

    void deleteOffer(Integer id);

    Offer getOfferById(Integer id);

    Page<Offer> getOffersByPage(int page);
}
