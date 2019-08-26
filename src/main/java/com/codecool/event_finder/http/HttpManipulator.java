package com.codecool.event_finder.http;


import com.codecool.event_finder.model.Event;
import com.codecool.event_finder.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class HttpManipulator {

    @Autowired
    private RestTemplate restTemplate;

    private String apiKey = "n9GXLEOjNlmtAZu82kZ9AxDre2KyrjAG";

    public ResponseEntity<Event> getEventResponseEntity(String cityName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://app.ticketmaster.com/discovery/v2/events.json?city=" +
                cityName +
                "&apikey=" + apiKey, HttpMethod.GET, entity, Event.class);
    }

    public ResponseEntity<Model> getModelResponseEntity(String countryCode) {
        return restTemplate.exchange("https://app.ticketmaster.com/discovery/v2/venues.json?countryCode=" +
                countryCode +
                "&apikey=" + apiKey, HttpMethod.GET,null, Model.class);
    }

    public ResponseEntity<Event> getCustomEventBySearch(HashMap<String, String> datas){
        StringBuilder url = new StringBuilder("http://app.ticketmaster.com/discovery/v2/events.json?");
        for (String key : datas.keySet()) {
            if (datas.get(key)!=null){
                url.append(key).append("=").append(datas.get(key)).append("&");
            }
        }
        url.append("apikey=").append(apiKey);
        String finalUrl = url.toString();
        return restTemplate.exchange(finalUrl, HttpMethod.GET, null, Event.class);
    }


}