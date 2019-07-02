package com.softserve.demo.controller;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.service.OfferService;
import com.softserve.demo.util.mappers.OfferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public OfferDTO createOffer(@RequestBody @Valid OfferDTO offerDTO) {
        System.out.println("!!!!!!!!!!" + offerDTO.getId());
        return offerMapper.offerToOfferDTO(
                offerService.createOffer(offerMapper.offerDTOToOffer(offerDTO)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVIDER', 'ROLE_CUSTOMER')")
    public List<OfferDTO> getAllOffers() {
        return offerMapper.offersToOfferDTOs(offerService.getAllOffers());
    }

    @GetMapping("customer/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<OfferDTO> getOffersByCustomerId(@PathVariable("id") Integer id) {
        return offerMapper.offersToOfferDTOs(offerService.getOffersByCustomerId(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public void deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOffer(id);
    }
}
