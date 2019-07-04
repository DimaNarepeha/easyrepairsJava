package com.softserve.demo.service;

import com.softserve.demo.model.Offer;

import java.util.List;

public interface OfferService {

    Offer createOffer(Offer offer);

    List<Offer> getAllOffers();

    void deleteOffer(Integer id);

    List<Offer> getOffersByCustomerId(Integer id);

    Offer updateOffer(Offer offer);
}
