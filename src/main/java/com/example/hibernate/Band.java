package com.example.hibernate;

import javax.persistence.*;

@Entity(name = "nj_metal")
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue
    int id;

    @Column(name="band_name")
    String band_name;

    @Column(name="year_formation")
    int year_formation;

    @Column(name = "genre")
    String genre;

    @Column(name = "city")
    String city;

    public Band (int bandId, String bandName, int yearFormation, String genre, String city) {
        super();
        this.id = bandId;
        this.band_name = bandName;
        this.year_formation = yearFormation;
        this.genre = genre;
        this.city = city;
    }

    public String getBandName () {
        return this.band_name;
    }

    public void setBandName (String bandName) {
        this.band_name = bandName;
    }

    public int getyearFormation () {
        return this.year_formation;
    }

    public void setBandName (int yearFormation) {
        this.year_formation = yearFormation;
    }

    public String getGenre () {
        return this.genre;
    }

    public void setGenre (String genre) {
        this.genre = genre;
    }

    public String getCity () {
        return this.city;
    }

    public void setCity (String city) {
        this.city = city;
    }
    
}

