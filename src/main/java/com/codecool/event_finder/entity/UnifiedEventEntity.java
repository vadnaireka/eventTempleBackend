package com.codecool.event_finder.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnifiedEventEntity {
    private EventEntity eventEntity;
    private Double averageRating;
}
