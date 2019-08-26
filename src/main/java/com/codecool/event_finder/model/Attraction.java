
package com.codecool.event_finder.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Attraction {

    private com.codecool.event_finder.model._links _links;
    private List<Classification> classifications;
    private ExternalLinks externalLinks;
    private String href;
    private String id;
    private List<Image> images;
    private String locale;
    private String name;
    private Boolean test;
    private String type;
    private UpcomingEvents upcomingEvents;
    private String url;

}
