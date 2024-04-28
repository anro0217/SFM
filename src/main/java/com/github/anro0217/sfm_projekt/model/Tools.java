package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NamedQuery(name = "find tool by id",
        query = "SELECT t FROM Tools t WHERE t.id = :id")
public class Tools {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="tool_id", nullable=false, unique=true)
    private int id;
    @Column(name="tool_name")
    private String toolName;
    @Column(name="is_available")
    boolean isAvailable;

    public Tools(String toolName, boolean isAvailable) {
        this.toolName = toolName;
        this.isAvailable = isAvailable;
    }
    public Tools() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    @Basic(optional = false)
    public String getName() {
        return toolName;
    }

    public void setName(String name) {
        this.toolName = name;
    }


    // ---- bat_id nem klappol ide szerintem, túl sok helyen szerepel.
    // ---- az világosnak tűnik h tools és allies között many-many rel van,
    // ---- de ehhez kéne kulcs mindkettőtől

    //@ManyToMany(mappedBy = "bat_id")
    //Set<Ally> toolOwners;

    @ManyToMany()
    private Set<Allies> toolOwners = new HashSet<>();

    public Set<Allies> getToolOwners() {
        return toolOwners;
    }

    public void setToolOwners(Set<Allies> toolOwners) {
        this.toolOwners = toolOwners;
    }

    // KÖVETKEZÖ SOROK Ally BA !!!
    /*

    import java.util.HashSet;
    import java.util.Set;

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
    */
}
