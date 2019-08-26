
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Event {

    private com.codecool.event_finder.model._embedded _embedded;
    private com.codecool.event_finder.model._links _links;
    private List<Classification> classifications;
    private Dates dates;
    private String id;
    private List<Image> images;
    private String locale;
    private String name;
    private String pleaseNote;
    private Promoter promoter;
    private List<Promoter> promoters;
    private Sales sales;
    private Boolean test;
    private String type;
    private String url;

}
