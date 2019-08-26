package com.codecool.event_finder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class EventEntity  {

//    @Id
//    @GeneratedValue
//    private Long id;

    @Id
    @Column(unique = true)
    private String id;
    private String locationName;
    private String eventName;
    private String city;
    private String address;
    private String address2;
    private String date;
    private String time;
    private String imageLink;
    private String facebookLink;
    private String webpageLink;
    private String instagramLink;
    private String youtubeLink;
    private String buyTicketUrl;

}
