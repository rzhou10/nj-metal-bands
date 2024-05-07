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

    public Band(String bandName, int yearFormation, String genre, String city) {
        this.bandName = bandName;
        this.yearFormation = yearFormation;
        this.genre = genre;
        this.city = city;
    }

    public String getBandName() {
        return this.bandName;
    }

    public int getYear() {
        return this.yearFormation;
    }

    public String getCity() {
        return this.city;
    }

    public String getGenre() {
        return this.genre;
    }
}