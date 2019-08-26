package com.codecool.event_finder.controller;

import com.codecool.event_finder.service.DataManipulator;
import com.codecool.event_finder.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping
public class VenueController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DataManipulator dataManipulator;

    @GetMapping(value = "/country/{country}", produces = "application/json")
    public List<Venue> getVenuesByCountry(@PathVariable("country") String countryCode) {
        return dataManipulator.getVenueList(countryCode);
    }
}