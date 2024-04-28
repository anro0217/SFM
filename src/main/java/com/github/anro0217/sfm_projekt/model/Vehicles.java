package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NamedQuery(name = "find vehicle by id" ,query = "Select v from Vehicles v where v.id = :id")
public class Vehicles {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="vehicle_id", nullable=false, unique=true)
    private int id;

    public Vehicles(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Vehicles() {
    }

    @Column(name="vehicle_name")
    private String vehicleName;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    @Basic(optional = false)
    public String getName() { return vehicleName; }

    public void setName(String vehicleName) { this.vehicleName = vehicleName; }

    @ManyToMany()
    private Set<Allies> vehicleOwners = new HashSet<>();

    public Set<Allies> getVehicleOwners() {
        return vehicleOwners;
    }

    public void setVehicleOwners(Set<Allies> vehicleOwners) {
        this.vehicleOwners = vehicleOwners;
    }

    // KÖVETKEZÖ SOROK Allies BA !
    /*
    import java.util.HashSet;
    import java.util.Set;

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
    */

}
