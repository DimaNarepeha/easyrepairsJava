package com.softserve.demo.controller;

import com.softserve.demo.model.Offer;
import com.softserve.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("offers")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        offerService.createOffer(offer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOffers() {
        return new ResponseEntity<>(offerService.getAllOffers(), HttpStatus.OK);
    }

    @GetMapping("{offerId}")
    public ResponseEntity<?> getOfferById(@PathVariable("offerId") Integer id) {
        return new ResponseEntity<>(offerService.getOfferById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOffer(@PathVariable("id") Integer id, @RequestBody Offer offer) {
        Offer offerUpdated = offerService.updateOffer(id, offer);

        if (offerUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(offerUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{offerId}")
    public ResponseEntity<?> deleteOfferById(@PathVariable("offerId") Integer id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Offer> getOffersByPage(@RequestParam(defaultValue = "0") int page) {
        return offerService.getOffersByPage(page);
    }
}
