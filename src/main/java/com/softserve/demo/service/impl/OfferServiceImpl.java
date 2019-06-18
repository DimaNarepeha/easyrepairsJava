package com.softserve.demo.service.impl;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final LocationRepository locationRepository;

    public OfferServiceImpl(OfferRepository offerRepository, LocationRepository locationRepository) {
        this.offerRepository = offerRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        offer.setLocation(saveLocation(offer.getLocation()));
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

    private Location saveLocation(final Location location) {
        Location locationFromDB = locationRepository.findLocationByCityAndCountryAndRegion(
                location.getCity(), location.getCountry(), location.getRegion());

        if (locationFromDB == null) {
            return locationRepository.save(location);
        }
        return locationFromDB;
    }
}
