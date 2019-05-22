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
    public void createOffer(Offer offer) {
        Location locationFromDB = locationRepository.findLocationByCityAndCountry(offer.getLocation().getCity(),
                offer.getLocation().getCountry(), offer.getLocation().getRegion());
        if (locationFromDB == null) {
            locationRepository.save(offer.getLocation());
            offerRepository.save(offer);
        } else {
            offer.getLocation().setId(locationFromDB.getId());
            offerRepository.save(offer);
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
}
