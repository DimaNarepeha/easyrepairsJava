package com.softserve.demo.controller;

import com.softserve.demo.model.Location;
import com.softserve.demo.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        locationService.createLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllLocations() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateLocation(@RequestBody Location location) {
        locationService.updateLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLocationById(@PathVariable("id") Integer id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Location> getLocationsByPage(@RequestParam(defaultValue = "0") int page) {
        return locationService.getLocationsByPage(page);
    }
}
