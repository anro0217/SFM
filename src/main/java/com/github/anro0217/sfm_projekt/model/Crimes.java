package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Crimes")
public class Crimes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crime_id")
    private int crimeId;

    @Column(name = "crime")
    private String crime;

    @Column(name = "civil_victims")
    private int civilVictims;

    @Column(name = "severity_level")
    private String severityLevel;

    @Column(name = "time")
    private Time time;

    @Column(name = "place")
    private String place;

    @ManyToMany()
    private Set<Villains> committedBy = new HashSet<>();

    public Crimes() {
        // Üres konstruktor
    }

    public Crimes(int crimeId, String crime, int civilVictims, String severityLevel, Time time, String place) {
        this.crimeId = crimeId;
        this.crime = crime;
        this.civilVictims = civilVictims;
        this.severityLevel = severityLevel;
        this.time = time;
        this.place = place;
    }

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public int getCivilVictims() {
        return civilVictims;
    }

    public void setCivilVictims(int civilVictims) {
        this.civilVictims = civilVictims;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Set<Villains> getCommittedBy() {
        return committedBy;
    }

    public void setCommittedBy(Set<Villains> committedBy) {
        this.committedBy = committedBy;
    }
}
