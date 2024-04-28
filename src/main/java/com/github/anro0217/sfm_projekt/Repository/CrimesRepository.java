package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.Crimes;
import com.github.zdanielm.sfm_projekt.model.Villains;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CrimesRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public CrimesRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public CrimesRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public CrimesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Crimes add(Crimes crime) {
        entityManager.getTransaction().begin();
        entityManager.persist(crime);
        entityManager.getTransaction().commit();
        return crime;
    }

    public Crimes find(int crime_id) {
        return entityManager.find(Crimes.class, crime_id);
    }

    public Crimes update(Crimes crime) {
        Crimes crimeToUpdate = find(crime.getCrimeId());
        entityManager.getTransaction().begin();
        crimeToUpdate.setCrime(crime.getCrime());
        crimeToUpdate.setCivilVictims(crime.getCivilVictims());
        crimeToUpdate.setSeverityLevel(crime.getSeverityLevel());
        crimeToUpdate.setTime(crime.getTime());
        crimeToUpdate.setPlace(crime.getPlace());
        entityManager.getTransaction().commit();
        return crimeToUpdate;
    }

    public void delete(Crimes crime) {
        entityManager.getTransaction().begin();
        entityManager.remove(crime);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }

    public void addVillain(int id, Villains villain) {
        Crimes crime = find(id);
        if(crime != null) {
            entityManager.getTransaction().begin();
            crime.getCommittedBy().add(villain);
            villain.getCommittedCrimes().add(crime);
            entityManager.getTransaction().commit();
        }
    }

    public void removeVillain(int id, Villains villain) {
        Crimes crime = find(id);
        if(crime != null) {
            entityManager.getTransaction().begin();
            crime.getCommittedBy().remove(villain);
            villain.getCommittedCrimes().remove(crime);
            entityManager.getTransaction().commit();
        }
    }
}
