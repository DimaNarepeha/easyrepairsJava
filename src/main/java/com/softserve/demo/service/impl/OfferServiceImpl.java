package com.softserve.demo.service.impl;

import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void createOffer(Offer offer) {
        offer.setStartDate(new java.sql.Date(new java.util.Date().getTime()));
        offerRepository.save(offer);
    }

    @Override  //TODO check how it works
    public void updateOffer(Offer offer) {
        Offer offerFromDB = offerRepository.getOne(offer.getId());
        if (offerFromDB != null) {
            offer.setStartDate(offerFromDB.getStartDate());
            offerRepository.saveAndFlush(offer);
        }
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Offer getOfferById(Integer id) {
        return offerRepository.getOne(id);
    }

    @Override
    public Page<Offer> getOffersByPage(int page) {
        return offerRepository.findAll(new PageRequest(page, 4));
    }
}
