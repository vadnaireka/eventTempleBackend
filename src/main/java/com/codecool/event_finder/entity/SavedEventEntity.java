package com.codecool.event_finder.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class SavedEventEntity {

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

    @Singular
    @EqualsAndHashCode.Exclude
    @ElementCollection
    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    private List<RatingEntity> ratings;

    @Singular
    @EqualsAndHashCode.Exclude
    @ElementCollection
    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CommentEntity> comments;

}