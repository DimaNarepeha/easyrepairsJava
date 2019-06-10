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

    private OfferRepository offerRepository;
    private LocationRepository locationRepository;

    public OfferServiceImpl(OfferRepository offerRepository, LocationRepository locationRepository) {
        this.offerRepository = offerRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        Location location = offer.getLocation();
        Location locationFromDB = locationRepository.findLocationByCityAndCountryAndRegion(
                location.getCity(), location.getCountry(), location.getRegion());
        if (locationFromDB == null) {
            locationRepository.save(location);
            return offerRepository.save(offer);
        }
        offer.setLocation(locationFromDB);
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
}
