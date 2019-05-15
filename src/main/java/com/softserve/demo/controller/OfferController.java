package com.softserve.demo.controller;

import com.softserve.demo.model.Offer;
import com.softserve.demo.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("offers")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        offerService.createOffer(offer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOffers() {
        return new ResponseEntity<>(offerService.getAllOffers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOfferById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(offerService.getOfferById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateOffer(@RequestBody Offer offer) {
        offerService.updateOffer(offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Offer> getOffersByPage(@RequestParam(defaultValue = "0") int page) {
        return offerService.getOffersByPage(page);
    }
}
