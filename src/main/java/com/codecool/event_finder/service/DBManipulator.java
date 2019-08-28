package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.CommentEntity;
import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.RatingEntity;
import com.codecool.event_finder.entity.SavedEventEntity;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.RatingRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}