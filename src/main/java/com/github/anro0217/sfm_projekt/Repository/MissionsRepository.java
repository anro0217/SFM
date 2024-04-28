package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Missions;
import com.github.zdanielm.sfm_projekt.model.Villains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MissionsRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public MissionsRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public MissionsRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public MissionsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Missions add(Missions mission) {
        entityManager.getTransaction().begin();
        entityManager.persist(mission);
        entityManager.getTransaction().commit();
        return mission;
    }

    public Missions find(int mission_id) {
        return entityManager.find(Missions.class, mission_id);
    }

    public Missions update(Missions mission) {
        Missions missionToUpdate = find(mission.getMission_id());
        entityManager.getTransaction().begin();
        missionToUpdate.setMission_id(mission.getMission_id());
        missionToUpdate.setName(mission.getName());
        missionToUpdate.setObjective(mission.getObjective());
        missionToUpdate.setSecurity_level(mission.getSecurity_level());
        missionToUpdate.setBackup_needs(mission.isBackup_needs());
        entityManager.getTransaction().commit();
        return missionToUpdate;
    }

    public void delete(Missions mission) {
        entityManager.getTransaction().begin();
        entityManager.remove(mission);
        entityManager.getTransaction().commit();
    }

    public void addAlly(int id, Allies ally) {
        Missions mission = find(id);
        if(mission != null) {
            entityManager.getTransaction().begin();
            mission.getHandledBy().add(ally);
            ally.setCurrent_mission(mission);
            entityManager.getTransaction().commit();
        }
    }

    public void removeAlly(int id, Allies ally) {
        Missions mission = find(id);
        if(mission != null) {
            entityManager.getTransaction().begin();
            mission.getHandledBy().remove(ally);
            ally.setCurrent_mission(null);
            entityManager.getTransaction().commit();
        }
    }

    public void addVillain(int id, Villains villain) {
        Missions mission = find(id);
        if(mission != null) {
            entityManager.getTransaction().begin();
            mission.getCurrentEnemies().add(villain);
            villain.setCurrentAffair(mission);
            entityManager.getTransaction().commit();
        }
    }

    public void removeVillain(int id, Villains villain) {
        Missions mission = find(id);
        if(mission != null) {
            entityManager.getTransaction().begin();
            mission.getCurrentEnemies().remove(villain);
            villain.setCurrentAffair(null);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
