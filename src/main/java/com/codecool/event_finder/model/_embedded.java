
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class _embedded {

    private List<Attraction> attractions;
    private List<Event> events;
    private List<Venue> venues;

}
