package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.RatingEntity;
import com.codecool.event_finder.entity.SavedEventEntity;
import com.codecool.event_finder.entity.UnifiedEventEntity;
import com.codecool.event_finder.http.HttpManipulator;
import com.codecool.event_finder.model.Event;
import com.codecool.event_finder.model.Model;
import com.codecool.event_finder.model.Venue;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class DataManipulator {

    @Autowired
    HttpManipulator httpManipulator;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SavedEventRepository savedEventRepository;

    @Autowired
    DataManipulator dataManipulator;

    public List<Venue> getVenueList(String countryCode) {
        ResponseEntity<Model> result = httpManipulator.getModelResponseEntity(countryCode);
        log.info("ez egy loooooooog!!!!!!");
        log.info(result.getBody().get_embedded().toString());
        return result.getBody().get_embedded().getVenues();
    }

    public Event getEventsByCity(String cityName) {
        return httpManipulator.getEventResponseEntity(cityName).getBody().get_embedded().getEvents().get(1);
    }

    public List<UnifiedEventEntity> getEventByCustomSearch(HashMap<String, String> datas) {
        eventRepository.deleteAll();
        ResponseEntity<Event> events = httpManipulator.getCustomEventBySearch(datas);
        try {
        saveEventToDatabase(events.getBody().get_embedded().getEvents());
        } catch (NullPointerException e) {
            log.warn(e.toString());
            return null;
        }
        return eventRepository.searchedEvents();
    }

    public List<SavedEventEntity> getSavedEvents() {
        return savedEventRepository.findAll();
    }

    public void saveEventToDatabase(List<Event> events) {
        for (Event event : events) {
            EventEntity eventToSave = getEventEntity(event);

            eventRepository.save(eventToSave);
        }
    }

    public String changeEventName(String name) {
        if (name.contains("|")) {
            int index = name.indexOf("|");
            return name.substring(0, index).trim();
        } else {
            return name;
        }
    }

    private EventEntity getEventEntity(Event event) {
        EventEntity eventToSave = EventEntity.builder()
                .eventName(changeEventName(event.getName()))
                .date(event.getDates().getStart().getLocalDate())
                .time(event.getDates().getStart().getLocalTime()).build();
        try {
            eventToSave.setId(event.get_embedded().getAttractions().get(0).getId());
        } catch (Exception e) {
            eventToSave.setId(event.getId());
        }
        try {
            eventToSave.setLocationName(event.get_embedded().getVenues().get(0).getName());
        } catch (Exception e) {
            eventToSave.setLocationName(null);
        }
        try {
            eventToSave.setAddress(event.get_embedded().getVenues().get(0).getAddress().getLine1());
        } catch (Exception e) {
            eventToSave.setAddress(null);
        }
        try {
            eventToSave.setAddress2(event.get_embedded().getVenues().get(0).getAddress().getLine2());
        } catch (Exception e) {
            eventToSave.setAddress2(null);
        }
        try {
            eventToSave.setCity(event.get_embedded().getVenues().get(0).getCity().getName());
        } catch (Exception e) {
            eventToSave.setCity(null);
        }
        try {
            eventToSave.setImageLink(event.getImages().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setImageLink("https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg");
        }
        try {
            eventToSave.setFacebookLink(event.get_embedded().getAttractions().get(0).getExternalLinks().getFacebook().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setFacebookLink(null);
        }
        try {
            eventToSave.setInstagramLink(event.get_embedded().getAttractions().get(0).getExternalLinks().getInstagram().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setInstagramLink(null);
        }
        try {
            eventToSave.setYoutubeLink(event.get_embedded().getAttractions().get(0).getExternalLinks().getYoutube().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setYoutubeLink(null);
        }
        try {
            eventToSave.setWebpageLink(event.get_embedded().getAttractions().get(0).getExternalLinks().getHomepage().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setWebpageLink(null);
        }
        try {
            eventToSave.setBuyTicketUrl(event.get_embedded().getAttractions().get(0).getUrl());
        } catch (Exception e) {
            eventToSave.setBuyTicketUrl(null);
        }
        return eventToSave;
    }

    public Double calculateAverageRating(SavedEventEntity entity){
        List<RatingEntity> ratings = entity.getRatings();
        List<Integer> integerRatingList = new ArrayList<>();
        for (RatingEntity rating : ratings) {
            int rate = rating.getRating();
            integerRatingList.add(rate);
        }
        Double sumOFRatings = integerRatingList.stream()
                .mapToDouble(a -> a)
                .sum();
        return sumOFRatings / integerRatingList.size();
    }

}
