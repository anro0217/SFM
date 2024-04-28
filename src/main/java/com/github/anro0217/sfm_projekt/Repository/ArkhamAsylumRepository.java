package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.ArkhamAsylum;
import com.github.zdanielm.sfm_projekt.model.Villains;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArkhamAsylumRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ArkhamAsylumRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public ArkhamAsylumRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public ArkhamAsylumRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ArkhamAsylum add(ArkhamAsylum asylum) {
        entityManager.getTransaction().begin();
        entityManager.persist(asylum);
        entityManager.getTransaction().commit();
        return asylum;
    }

    public ArkhamAsylum find(int villain_id) {
        return entityManager.find(ArkhamAsylum.class, villain_id);
    }

    public ArkhamAsylum update(ArkhamAsylum asylum) {
        ArkhamAsylum asylumToUpdate = find(asylum.getSectionId());
        entityManager.getTransaction().begin();
        // Frissítsd az attribútumokat a megfelelő setterekkel
        asylumToUpdate.setSection(asylum.getSection());
        asylumToUpdate.setTreatedBy(asylum.getTreatedBy());
        asylumToUpdate.setMentalIllness(asylum.getMentalIllness());
        asylumToUpdate.setNeedsTreatment(asylum.isNeedsTreatment());
        asylumToUpdate.setCellNumber(asylum.getCellNumber());
        entityManager.getTransaction().commit();
        return asylumToUpdate;
    }

    public void delete(ArkhamAsylum asylum) {
        entityManager.getTransaction().begin();
        entityManager.remove(asylum);
        entityManager.getTransaction().commit();
    }

    public void addPatient(int id, Villains villain) {
        ArkhamAsylum asylum = find(id);
        if(asylum != null) {
            entityManager.getTransaction().begin();
            asylum.getPatients().add(villain);
            villain.setTreatedAt(asylum);
            entityManager.getTransaction().commit();
        }
    }

    public void removePatient(int id, Villains villain) {
        ArkhamAsylum asylum = find(id);
        if(asylum != null) {
            entityManager.getTransaction().begin();
            asylum.getPatients().remove(villain);
            villain.setCurrentAffair(null);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
