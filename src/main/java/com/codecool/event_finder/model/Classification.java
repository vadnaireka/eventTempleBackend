
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Classification {

    private Boolean family;
    private Genre genre;
    private Boolean primary;
    private Segment segment;
    private SubGenre subGenre;
    private SubType subType;
    private Type type;

}
