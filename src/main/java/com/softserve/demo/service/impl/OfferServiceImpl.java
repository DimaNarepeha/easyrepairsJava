package com.softserve.demo.service.impl;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Location locationFromDB = locationRepository.findLocationByCityAndCountry(
                location.getCity(), location.getCountry(), location.getRegion());
        if (locationFromDB == null) {
            locationRepository.save(location);
            return offerRepository.save(offer);
        } else {
            offer.getLocation().setId(locationFromDB.getId());
            return offerRepository.save(offer);
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
    public List<Offer> getOffersByCustomerId(Integer id) {
        return offerRepository.findAllByCustomerId(id);
    }
}
