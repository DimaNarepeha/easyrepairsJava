package com.softserve.demo.service.Impl;

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

    @Autowired
    OfferRepository offerRepository;

    @Override
    public void createOffer(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Integer id, Offer offer) {
        if (offerRepository.existsById(id)) {
            Offer offerFromDB = offerRepository.getOne(id);
            offerFromDB.setCustomer(offer.getCustomer());
            offerFromDB.setStartDate(offer.getStartDate());
            offerFromDB.setDescription(offer.getDescription());
            offerFromDB.setLocation(offer.getLocation());
            offerRepository.save(offerFromDB);
            return offerRepository.getOne(id);
        }
        return null;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer deleteOffer(Integer id) {
        if (offerRepository.existsById(id)) {
            Offer offerFromDB = offerRepository.getOne(id);
            offerRepository.deleteById(id);
            return offerFromDB;
        }
        return null;
    }

    @Override
    public Offer getOfferById(Integer id) {
        if (offerRepository.existsById(id)) {
            return offerRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Page<Offer> getOffersByPage(int page) {
        return offerRepository.findAll(new PageRequest(page, 4));
    }
}
