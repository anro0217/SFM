package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Arkham_asylum")
public class ArkhamAsylum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "section_id")
    private int sectionId;

    @Column(name = "section")
    private String section;

    @Column(name = "treated_by")
    private String treatedBy;

    @Column(name = "mental_illness")
    private String mentalIllness;

    @Column(name = "needs_treatment")
    private boolean needsTreatment;

    @Column(name = "cell_number")
    private int cellNumber;

    @OneToMany(targetEntity = Villains.class)
    private Set<Villains> patients = new HashSet<>();

    public ArkhamAsylum() {
        // Üres konstruktor
    }

    public ArkhamAsylum(String section, String treatedBy, String mentalIllness, boolean needsTreatment, int cellNumber) {
        this.section = section;
        this.treatedBy = treatedBy;
        this.mentalIllness = mentalIllness;
        this.needsTreatment = needsTreatment;
        this.cellNumber = cellNumber;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTreatedBy() {
        return treatedBy;
    }

    public void setTreatedBy(String treatedBy) {
        this.treatedBy = treatedBy;
    }

    public String getMentalIllness() {
        return mentalIllness;
    }

    public void setMentalIllness(String mentalIllness) {
        this.mentalIllness = mentalIllness;
    }

    public boolean isNeedsTreatment() {
        return needsTreatment;
    }

    public void setNeedsTreatment(boolean needsTreatment) {
        this.needsTreatment = needsTreatment;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Set<Villains> getPatients() {
        return patients;
    }

    public void setPatients(Set<Villains> patients) {
        this.patients = patients;
    }
}
