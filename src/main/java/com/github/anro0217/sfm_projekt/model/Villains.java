package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "villains")
public class Villains {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "villain_id")
    private int villain_id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthname")
    private String birthname;

    @Column(name = "mothers_name")
    private String mothers_name;

    @Column(name = "date_of_birth")
    private LocalDate date_of_birth;

    @Column(name = "weight")
    private int weight;

    @Column(name = "height")
    private int height;

    @Column(name = "danger_level")
    private int danger_level;

    @Column(name = "is_metahuman")
    private boolean is_metahuman;

    @Column(name = "special_ability")
    private String special_ability;

    @Column(name = "weakness")
    private String weakness;

    @Column(name = "blood_type")
    private String blood_type;

    @Column(name = "num_of_victims")
    private int num_of_victims;

    @Column(name = "location")
    private String location;

    @Column(name = "is_detained")
    private boolean is_detained;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Missions currentAffair;

    @ManyToOne
    private ArkhamAsylum treatedAt;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "villains_crimes",
            joinColumns = {@JoinColumn(name = "villain_id")},
            inverseJoinColumns = { @JoinColumn(name = "crime_id")},
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = {"villain_id", "crime_id"}
                    )
            }
    )
    private Set<Crimes> committedCrimes = new HashSet<>();

    public int getVillain_id() {
        return villain_id;
    }

    public void setVillain_id(int villain_id) {
        this.villain_id = villain_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthname() {
        return birthname;
    }

    public void setBirthname(String birthname) {
        this.birthname = birthname;
    }

    public String getMothers_name() {
        return mothers_name;
    }

    public void setMothers_name(String mothers_name) {
        this.mothers_name = mothers_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDanger_level() {
        return danger_level;
    }

    public void setDanger_level(int danger_level) {
        this.danger_level = danger_level;
    }

    public boolean isIs_metahuman() {
        return is_metahuman;
    }

    public void setIs_metahuman(boolean is_metahuman) {
        this.is_metahuman = is_metahuman;
    }

    public String getSpecial_ability() {
        return special_ability;
    }

    public void setSpecial_ability(String special_ability) {
        this.special_ability = special_ability;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getNum_of_victims() {
        return num_of_victims;
    }

    public void setNum_of_victims(int num_of_victims) {
        this.num_of_victims = num_of_victims;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isIs_detained() {
        return is_detained;
    }

    public void setIs_detained(boolean is_detained) {
        this.is_detained = is_detained;
    }

    public String getDescription() {return description; }

    public void setDescription(String description) {this.description = description;}

    public Missions getCurrentAffair() {
        return currentAffair;
    }

    public void setCurrentAffair(Missions currentAffair) {
        this.currentAffair = currentAffair;
    }

    public ArkhamAsylum getTreatedAt() {
        return treatedAt;
    }

    public void setTreatedAt(ArkhamAsylum treatedAt) {
        this.treatedAt = treatedAt;
    }

    public Set<Crimes> getCommittedCrimes() {
        return committedCrimes;
    }

    public void setCommittedCrimes(Set<Crimes> committedCrimes) {
        this.committedCrimes = committedCrimes;
    }
}
