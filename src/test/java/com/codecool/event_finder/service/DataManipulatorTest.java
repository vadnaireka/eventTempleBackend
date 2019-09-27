package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.http.HttpManipulator;
import com.codecool.event_finder.model.Event;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class DataManipulatorTest {


    @Autowired
    HttpManipulator httpManipulator;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SavedEventRepository savedEventRepository;

    @Autowired
    DataManipulator dataManipulator;

//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getVenueList() {
//    }
//
//    @Test
//    void getEventsByCity() {
//
//    }
//
//    @Test
//    void getEventByCustomSearch() {
//    }
//
//    @Test
//    void getSavedEvents() {
//    }
//
    @Test
    public void changeEventName(){
        String badname = "Ariana Grande | Platinum tickets";
        System.out.println(dataManipulator.changeEventName(badname));
     //   assertEquals("Ariana Grande", dataManipulator.changeEventName(badname));
    }


    @Test
    @Transactional
    public void saveEventToDatabase() {
        eventRepository.deleteAll();
        EventEntity abcd = EventEntity.builder().id("ABCD").build();
        eventRepository.save(abcd);
        assertThat(eventRepository.count()).isEqualTo(1);
    }
}