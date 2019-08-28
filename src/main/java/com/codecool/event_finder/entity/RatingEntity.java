package com.codecool.event_finder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class RatingEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Integer rating;
    @ManyToOne
    private SavedEventEntity event;

}
