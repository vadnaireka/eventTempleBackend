package com.codecool.event_finder.controller;

import com.codecool.event_finder.model.Event;
import com.codecool.event_finder.service.DBManipulator;
import com.codecool.event_finder.service.DataManipulator;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class EventControllerTest {

    private Event testEvent;

    @Autowired
    EventController eventController;

    @Autowired
    RestTemplate restTemplate;

    @MockBean
    DataManipulator dataManipulator;

    @MockBean
    DBManipulator dbManipulator;

    @Before
    public void setUp() {
        testEvent = new Event();
        when(dataManipulator.getEventsByCity("randomString")).thenReturn(testEvent);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getEventByCity() {
        Event result = eventController.getEventByCity("randomString");

        Assertions.assertEquals(testEvent, result);
    }

    @Test
    void saveEventToDatabase() {
    }

    @Test
    void getEventByCustomSearch() {
    }

    @Test
    void getSavedEvents() {
    }
}