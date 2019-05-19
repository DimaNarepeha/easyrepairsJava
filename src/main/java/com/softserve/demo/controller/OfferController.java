package com.softserve.demo.controller;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.service.OfferService;
import com.softserve.demo.util.OfferMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("offers")
public class OfferController {

    private final OfferService offerService;
    private final OfferMapper offerMapper;

    public OfferController(OfferService offerService, OfferMapper offerMapper) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody OfferDTO offerDTO) {
        offerService.createOffer(offerMapper.OfferDTOToOffer(offerDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllOffers() {
        return new ResponseEntity<>(
                offerMapper.OffersToOfferDTOs(offerService.getAllOffers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOfferById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                offerMapper.OfferToOfferDTO(offerService.getOfferById(id)), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateOffer(@RequestBody OfferDTO offerDTO) {
        offerService.updateOffer(offerMapper.OfferDTOToOffer(offerDTO));
        return new ResponseEntity<>(offerDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
