package com.softserve.demo.service;

import com.softserve.demo.model.Offer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfferService {

    void createOffer(Offer offer);

    Offer updateOffer(Integer id, Offer offer);

    List<Offer> getAllOffers();

    Offer deleteOffer(Integer id);

    Offer getOfferById(Integer id);

    Page<Offer> getOffersByPage(int page);
}
