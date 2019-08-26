
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class _links {

    private List<Attraction> attractions;
    private Self self;
    private List<Venue> venues;

}
