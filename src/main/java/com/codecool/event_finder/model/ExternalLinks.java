
package com.codecool.event_finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class ExternalLinks {

    private List<Facebook> facebook;
    private List<Homepage> homepage;
    private List<Instagram> instagram;
    private List<Itune> itunes;
    private List<Lastfm> lastfm;
    private List<Musicbrainz> musicbrainz;
    private List<Twitter> twitter;
    private List<Wiki> wiki;
    private List<Youtube> youtube;

}
