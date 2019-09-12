package com.codecool.event_finder.controller;

import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.SavedEventEntity;
import com.codecool.event_finder.entity.UnifiedEventEntity;
import com.codecool.event_finder.model.Event;
import com.codecool.event_finder.service.DBManipulator;
import com.codecool.event_finder.service.DataManipulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
@CrossOrigin
public class EventController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DataManipulator dataManipulator;
    @Autowired
    DBManipulator dbManipulator;



    @GetMapping(value = "/event/{city}", produces = "application/json")
    public Event getEventByCity(@PathVariable("city") String cityName) {
        return dataManipulator.getEventsByCity(cityName);
    }

    @PostMapping(path = "/save/")
    public void saveEventToDatabase(@RequestBody Map<String, String> eventEntity) {
        dbManipulator.saveToDatabase(eventEntity.get("eventEntity"));
    }

    @PostMapping(path = "/saverating/")
    public void saveRating(@RequestBody Map<String, String> data) {
        dbManipulator.saveRating(data.get("eventEntityId"), Integer.parseInt(data.get("rating")));
    }

    @GetMapping(value = "/search/{city}/{startDateTime}/{endDateTime}/{keyword}", produces = "application/json")
    public List<UnifiedEventEntity> getEventByCustomSearch(@PathVariable("city") String cityName,
                                                           @PathVariable("startDateTime") String startDateTime,
                                                           @PathVariable("endDateTime") String endDateTime,
                                                           @PathVariable("keyword") String keyword) {
        startDateTime+="T00:00:01Z";
        endDateTime+="T23:59:59Z";
        HashMap<String, String> datas = new HashMap<>();
        datas.put("city", cityName);
        datas.put("startDateTime", startDateTime);
        datas.put("endDateTime", endDateTime);
        datas.put("keyword", keyword);

        return dataManipulator.getEventByCustomSearch(datas);
    }

    @GetMapping(value = "/saved", produces = "application/json")
    public List<SavedEventEntity> getSavedEvents() {
        return dataManipulator.getSavedEvents();
    }

    @PostMapping(value = "/deleteSavedEvent/", consumes = "application/json")
    public void deleteSavedEvent(@RequestBody HashMap<String, String> data) {
        log.info("--------------------------THIS IS THE ID TO DELETE ---------------------");
        log.info(data.toString());
        dbManipulator.deleteSavedEvent(data.get("data"));
    }
}