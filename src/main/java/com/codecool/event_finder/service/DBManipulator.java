package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.*;
import com.codecool.event_finder.repository.AppUserRepository;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.RatingRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DBManipulator {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SavedEventRepository savedEventRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    DataManipulator dataManipulator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserRepository appUserRepository;


    public void saveToDatabase(String eventID) {
        EventEntity eventEntity = eventRepository.findDistinctByIdLike(eventID);
        SavedEventEntity build = SavedEventEntity.builder()
                .id(eventEntity.getId())
                .address(eventEntity.getAddress())
                .address2(eventEntity.getAddress2())
                .buyTicketUrl(eventEntity.getBuyTicketUrl())
                .city(eventEntity.getCity())
                .date(eventEntity.getDate())
                .eventName(eventEntity.getEventName())
                .facebookLink(eventEntity.getFacebookLink())
                .imageLink(eventEntity.getImageLink())
                .instagramLink(eventEntity.getInstagramLink())
                .locationName(eventEntity.getLocationName())
                .time(eventEntity.getTime())
                .webpageLink(eventEntity.getWebpageLink())
                .youtubeLink(eventEntity.getYoutubeLink())
                .build();
        savedEventRepository.save(build);

    }

    @Transactional
    public void saveRating(String eventID, Integer rate) {
        SavedEventEntity updatable = savedEventRepository.findDistinctByIdLike(eventID);
        if (updatable == null) {
            saveToDatabase(eventID);
            updatable = savedEventRepository.findDistinctByIdLike(eventID);
        }
        RatingEntity buildedRating = RatingEntity.builder()
                .event(updatable)
                .rating(rate)
                .build();
        assert updatable != null;
        List<RatingEntity> ratings = updatable.getRatings();
        ratings.add(buildedRating);
        Double averageRating = dataManipulator.calculateAverageRating(updatable);
        updatable.setAveragerating(averageRating);
        savedEventRepository.save(updatable);
    }

    public void saveNewUser(String username, String password){
        AppUser newuser = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER")
                .build();
        appUserRepository.save(newuser);
    }

    public void deleteSavedEvent(String eventId) {
        SavedEventEntity deletableSavedEntity = savedEventRepository.findDistinctById(eventId);
        savedEventRepository.delete(deletableSavedEntity);
    }
}