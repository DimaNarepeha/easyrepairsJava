package com.softserve.demo.controller;

import com.softserve.demo.dto.OfferDTO;
import com.softserve.demo.model.Offer;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.LocationService;
import com.softserve.demo.service.OfferService;
import com.softserve.demo.service.ServiceFromProviders;
import com.softserve.demo.util.CustomerMapper;
import com.softserve.demo.util.LocationMapper;
import com.softserve.demo.util.OfferMapper;
import com.softserve.demo.util.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("offers")
public class OfferController {

    private final OfferService offerService;
    private final LocationService locationService;
    private final CustomerService customerService;
    private final ServiceFromProviders serviceFromProviders;
    private final OfferMapper offerMapper;
    private final LocationMapper locationMapper;
    private final CustomerMapper customerMapper;
    private final ServiceMapper serviceMapper;

    public OfferController(OfferService offerService, LocationService locationService,
                           CustomerService customerService, ServiceFromProviders serviceFromProviders,
                           OfferMapper offerMapper, LocationMapper locationMapper,
                           CustomerMapper customerMapper, ServiceMapper serviceMapper) {

        this.offerService = offerService;
        this.locationService = locationService;
        this.customerService = customerService;
        this.serviceFromProviders = serviceFromProviders;
        this.offerMapper = offerMapper;
        this.locationMapper = locationMapper;
        this.customerMapper = customerMapper;
        this.serviceMapper = serviceMapper;
    }

    @PostMapping("create")
    public ResponseEntity<?> createOffer(@RequestBody /*@Valid*/ OfferDTO offerDTO) {
        offerService.createOffer(convertToOffer(offerDTO));
        return new ResponseEntity<>(offerDTO, HttpStatus.CREATED);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllOffers() {
        return new ResponseEntity<>(
                offerService.getAllOffers().stream().map(
                        this::convertToOfferDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Offer convertToOffer(OfferDTO offerDTO) {
        Offer offer = offerMapper.OfferDTOToOffer(offerDTO);
        offer.setLocation(locationMapper.LocationDTOToLocation(offerDTO.getLocationDTO()));
        offer.setCustomer(customerMapper.CustomerDTOToCustomer(offerDTO.getCustomerDTO()));
        offer.setServices(serviceMapper.ServiceDTOsToService(offerDTO.getServiceDTOs()));
        return offer;
    }

    private OfferDTO convertToOfferDTO(Offer offer) {
        OfferDTO offerDTO = offerMapper.OfferToOfferDTO(offer);
        offerDTO.setLocationDTO(locationMapper.LocationToLocationDTO(locationService.getLocationByOffer(offer)));
        offerDTO.setCustomerDTO(customerMapper.CustomerToCustomerDTO(customerService.getCustomerByOffer(offer)));
        offerDTO.setServiceDTOs(serviceMapper.ServicesToServiceDTOs(serviceFromProviders.getAllByOffer(offer)));
        return offerDTO;
    }
}
