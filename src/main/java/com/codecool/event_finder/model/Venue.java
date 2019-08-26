
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Venue {

    private com.codecool.event_finder.model._links _links;
    private String accessibleSeatingDetail;
    private Address address;
    private City city;
    private Country country;
    private String href;
    private String id;
    private List<Image> images;
    private String locale;
    private Location location;
    private String name;
    private String postalCode;
    private Boolean test;
    private String timezone;
    private String type;
    private UpcomingEvents upcomingEvents;
    private String url;

}
