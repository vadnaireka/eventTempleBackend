package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.CommentEntity;
import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.SavedEventEntity;
import com.codecool.event_finder.repository.CommentRepository;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBManipulator  {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SavedEventRepository savedEventRepository;



    public void saveToDatabase(String eventID) {
        EventEntity eventEntity = eventRepository.findDistinctByIdLike(eventID);
        System.out.println(eventEntity);
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
        System.out.println(build);

        savedEventRepository.save(build);

    }
}
