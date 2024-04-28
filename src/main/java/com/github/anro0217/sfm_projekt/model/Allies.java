package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.github.zdanielm.sfm_projekt.model.Gotham_knights;

@Entity
@Table(name = "Allies")
public class Allies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ally_id")
    private int bat_id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_available")
    private boolean is_available;

    @Column(name = "is_metahuman")
    private boolean is_metahuman;

    @Column(name = "special_ability")
    private String special_ability;

    @ManyToOne
    private Missions current_mission;

    @OneToOne
    @JoinColumn(name = "knight_id", unique = true)
    private Gotham_knights gothamKnight;

    public Allies() {

    }

    public Allies(String name, boolean is_available, boolean is_metahuman, String special_ability) {
        this.name = name;
        this.is_available = is_available;
        this.is_metahuman = is_metahuman;
        this.special_ability = special_ability;
    }

    public int getBat_id() {
        return bat_id;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public boolean isIs_metahuman() {
        return is_metahuman;
    }

    public String getSpecial_ability() {
        return special_ability;
    }

    public int getCurrent_mission() {
        return (current_mission != null) ? current_mission.getMission_id() : 0;
    }

    public void setBat_id(int bat_id) {
        this.bat_id = bat_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public void setIs_metahuman(boolean is_metahuman) {
        this.is_metahuman = is_metahuman;
    }

    public void setSpecial_ability(String special_ability) {
        this.special_ability = special_ability;
    }

    public void setCurrent_mission(Missions current_mission) {
        this.current_mission = current_mission;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "toolToOwners",
            joinColumns =  { @JoinColumn(name = "ally_id") },
            inverseJoinColumns = { @JoinColumn(name = "tool_id") },
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "ally_id", "tool_id" }
                    )
            }

    )
    private Set<Tools> toolSet = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "vehiclesToOwners",
            joinColumns =  { @JoinColumn(name = "ally_id") },
            inverseJoinColumns = { @JoinColumn(name = "vehicle_id") },
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "ally_id", "vehicle_id" }
                    )
            }

    )
    private Set<Vehicles> vehicleSet = new HashSet<>();

    public Gotham_knights getGothamKnight() {
        return gothamKnight;
    }

    public void setGothamKnight(Gotham_knights gothamKnight) {
        this.gothamKnight = gothamKnight;
    }

    public Set<Tools> getToolSet() {
        return toolSet;
    }

    public void setToolSet(Set<Tools> toolSet) {
        this.toolSet = toolSet;
    }

    public Set<Vehicles> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicles> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }
}
