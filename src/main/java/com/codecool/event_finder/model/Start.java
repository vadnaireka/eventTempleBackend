
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Start {

    private Boolean dateTBA;
    private Boolean dateTBD;
    private String dateTime;
    private String localDate;
    private String localTime;
    private Boolean noSpecificTime;
    private Boolean timeTBA;

}
