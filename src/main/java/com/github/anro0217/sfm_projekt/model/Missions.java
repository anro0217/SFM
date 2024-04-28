package com.github.zdanielm.sfm_projekt.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Missions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mission_id")
    private int mission_id;

    private String name;

    private String objective;

    private String security_level;

    private boolean Backup_needs;

    @OneToMany(targetEntity = Allies.class)
    private Set<Allies> handledBy = new HashSet<>();

    @OneToMany(targetEntity = Villains.class)
    private Set<Villains> currentEnemies = new HashSet<>();

    public int getMission_id(){
        return mission_id;
    }
    public String getName(){
        return name;
    }
    public String getObjective() {
        return objective;
    }
    public boolean isBackup_needs() {
        return Backup_needs;
    }
    public String getSecurity_level() {
        return security_level;
    }
    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }
    public void setBackup_needs(boolean backup_needs) {
        Backup_needs = backup_needs;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
    public void setSecurity_level(String security_level) {
        this.security_level = security_level;
    }

    public Set<Allies> getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(Set<Allies> handledBy) {
        this.handledBy = handledBy;
    }

    public Set<Villains> getCurrentEnemies() {
        return currentEnemies;
    }

    public void setCurrentEnemies(Set<Villains> currentEnemies) {
        this.currentEnemies = currentEnemies;
    }
}
