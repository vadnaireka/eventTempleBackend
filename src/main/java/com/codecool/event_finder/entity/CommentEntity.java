package com.codecool.event_finder.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;
    private String user;
    private String commentText;

    @ManyToOne
    private SavedEventEntity event;

}
