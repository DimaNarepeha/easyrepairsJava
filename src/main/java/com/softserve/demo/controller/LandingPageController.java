package com.softserve.demo.controller;


import com.softserve.demo.dto.ServiceProviderInfoDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.Service;
import com.softserve.demo.model.search.ProviderCriteria;
import com.softserve.demo.repository.ServicesRepository;
import com.softserve.demo.util.ProviderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("landing-page")
@ResponseBody
public class LandingPageController {
   private final ProviderFilter filter;
   private  final ProviderInfoMapper mapper;

    @Autowired
    ServicesRepository sericee;

    @Autowired
    public LandingPageController(ProviderFilter filter, ProviderInfoMapper mapper) {
        this.filter = filter;
        this.mapper = mapper;
    }


    @GetMapping("test")
    public ResponseEntity<List<ServiceProviderInfoDTO>> test() {

        List<Service> ss = new ArrayList<>();
        ss.add(sericee.getOne(1));
        ss.add(sericee.getOne(3));

        ProviderCriteria criteria = new ProviderCriteria();
        criteria.setCity("Lviv");
        criteria.setMaxRaiting(4);
        criteria.setMinRaiting(1);
        criteria.setStatus(ProviderStatus.APPROVED);
        criteria.setServices(ss);

        return new ResponseEntity<>(mapper.map(filter.findByListServices(criteria)), HttpStatus.OK);
    }


    @GetMapping("all-approved")
    public ResponseEntity<List<ServiceProviderInfoDTO>> getAllApproved(){
        return new ResponseEntity<>(mapper.map(filter.findAllApproved()), HttpStatus.OK);
    }

}

