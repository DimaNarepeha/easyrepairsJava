package com.softserve.demo.service.impl;

import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
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
        offerRepository.save(offer);
    }

    @Override
    public void updateOffer(Offer offer) {
        Offer offerFromDB;
        if ((offerFromDB = offerRepository.getOne(offer.getId())) != null) {
            offerFromDB.setCustomer(offer.getCustomer());
            offerFromDB.setStartDate(offer.getStartDate());
            offerFromDB.setDescription(offer.getDescription());
            offerFromDB.setLocation(offer.getLocation());
            offerRepository.save(offerFromDB);
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
