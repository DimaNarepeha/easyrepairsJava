package com.softserve.demo.service;

import com.softserve.demo.model.Offer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfferService {

    void createCustomer(Offer offer);
    Offer updateOffer(Long id, Offer offer);
    List<Offer> getAllOffers();
    Offer deleteOffer(Long id);
    Offer getOfferById(Long id);
    Page<Offer> getOffersByPage(int page);
}
