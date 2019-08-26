
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Dates {

    private Boolean spanMultipleDays;
    private Start start;
    private Status status;
    private String timezone;

}
