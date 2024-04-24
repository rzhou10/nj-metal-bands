package com.example.hibernate;

import javax.persistence.*;

@Entity(name = "nj_metal")
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue
    int id;

    @Column(name = "band_name")
    String bandName;

    @Column(name = "year_formation")
    int yearFormation;

    @Column(name = "genre")
    String genre;

    @Column(name = "city")
    String city;

    public Band(int bandId, String bandName, int yearFormation, String genre, String city) {
        super();
        this.id = bandId;
        this.bandName = bandName;
        this.yearFormation = yearFormation;
        this.genre = genre;
        this.city = city;
    }

    public String getBandName() {
        return this.bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getyearFormation() {
        return this.yearFormation;
    }

    public void setBandName(int yearFormation) {
        this.yearFormation = yearFormation;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
