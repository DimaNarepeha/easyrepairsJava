package com.softserve.demo.controller;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.service.OfferService;
import com.softserve.demo.util.OfferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("offers")
@Slf4j
public class OfferController {

    private final OfferService offerService;
    private final OfferMapper offerMapper;

    public OfferController(OfferService offerService, OfferMapper offerMapper) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDTO createOffer(@RequestBody @Valid OfferDTO offerDTO) {
        return offerMapper.offerToOfferDTO(
                offerService.createOffer(offerMapper.offerDTOToOffer(offerDTO)));
    }

    @GetMapping
    public List<OfferDTO> getAllOffers() {
        return offerMapper.offersToOfferDTOs(offerService.getAllOffers());
    }

    @GetMapping("customer/{id}")
    public List<OfferDTO> getOffersByCustomer(@PathVariable("id") Integer id) {
        return offerMapper.offersToOfferDTOs(offerService.getOffersByCustomerId(id));
    }

    @DeleteMapping("/{id}")
    public void deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOffer(id);
    }
}
