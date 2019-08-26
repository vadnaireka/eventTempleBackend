package com.codecool.event_finder.service;

import com.codecool.event_finder.http.HttpManipulator;
import com.codecool.event_finder.repository.EventRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class DataManipulatorTest {


    @MockBean
    HttpManipulator httpManipulator;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    SavedEventRepository savedEventRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getVenueList() {
    }

    @Test
    void getEventsByCity() {

    }

    @Test
    void getEventByCustomSearch() {
    }

    @Test
    void getSavedEvents() {
    }

    @Test
    void saveEventToDatabase() {
    }
}