package com.example.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    @JsonProperty("band_name")
    private String bandName;
    @JsonProperty("year_formation")
    private int yearFormation;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("city")
    private String city;

    public Band () {}

    public String getBandName () {
        return this.bandName;
    }
}