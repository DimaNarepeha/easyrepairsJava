package com.softserve.demo.controller;

import com.softserve.demo.model.Location;
import com.softserve.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("location")
public class LocationController {
    
    @Autowired
    LocationService locationService;

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        locationService.createLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllLocations() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("{locationId}")
    public ResponseEntity<?> getLocationById(@PathVariable("locationId") Integer id) {
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") Integer id, @RequestBody Location location) {
        Location locationUpdated = locationService.updateLocation(id, location);

        if (locationUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(locationUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{locationId}")
    public ResponseEntity<?> deleteLocationById(@PathVariable("locationId") Integer id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Location> getLocationsByPage(@RequestParam(defaultValue = "0") int page) {
        return locationService.getLocationsByPage(page);
    }
}
