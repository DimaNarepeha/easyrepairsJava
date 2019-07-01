package com.softserve.demo.service.impl;

import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.LocationService;
import com.softserve.demo.service.OfferService;
import com.softserve.demo.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final LocationService locationService;

    public OfferServiceImpl(OfferRepository offerRepository, LocationService locationService) {
        this.offerRepository = offerRepository;
        this.locationService = locationService;
    }

    @Override
    public Offer createOffer(Offer offer) {
        offer.setLocation(locationService.saveLocationIfNotExist(offer.getLocation()));
        offer.setRemoveDate(LocalDate.from(LocalDate.now().plusDays(Constant.OFFER_DATE_OUT)));
        return offerRepository.save(offer);
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
    public List<Offer> getOffersByCustomerId(Integer id) {
        return offerRepository.findAllByCustomerId(id);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        offer.setLocation(locationService.saveLocationIfNotExist(offer.getLocation()));
        return offerRepository.save(offer);
    }
}
