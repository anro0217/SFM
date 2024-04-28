package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlliesRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public AlliesRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public AlliesRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public AlliesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Allies add(Allies ally) {
        entityManager.getTransaction().begin();
        entityManager.persist(ally);
        entityManager.getTransaction().commit();
        return ally;
    }

    public Allies find(int bat_id) {
        return entityManager.find(Allies.class, bat_id);
    }

    public Allies update(Allies ally) {
        Allies allyToUpdate = find(ally.getBat_id());
        entityManager.getTransaction().begin();
        allyToUpdate.setName(ally.getName());
        allyToUpdate.setIs_available(ally.isIs_available());
        allyToUpdate.setIs_metahuman(ally.isIs_metahuman());
        allyToUpdate.setSpecial_ability(ally.getSpecial_ability());
        //allyToUpdate.setCurrent_mission(ally.getCurrent_mission());
        entityManager.getTransaction().commit();
        return allyToUpdate;
    }

    public void delete(Allies ally) {
        entityManager.getTransaction().begin();
        entityManager.remove(ally);
        entityManager.getTransaction().commit();
    }

    public void addCurrentMission(int id, Missions mission) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.setCurrent_mission(mission);
            mission.getHandledBy().add(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void removeCurrentMission(int id, Missions mission) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.setCurrent_mission(null);
            mission.getHandledBy().remove(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void addGothamKnight(int id, Gotham_knights gothamKnight) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.setGothamKnight(gothamKnight);
            gothamKnight.setAlly(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void removeGothamKnight(int id, Gotham_knights gothamKnight) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.setGothamKnight(null);
            gothamKnight.setAlly(null);
            entityManager.getTransaction().commit();
        }
    }

    public void addTool(int id, Tools tool) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.getToolSet().add(tool);
            tool.getToolOwners().add(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void removeTool(int id, Tools tool) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.getToolSet().remove(tool);
            tool.getToolOwners().remove(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void addVehicle(int id, Vehicles vehicle) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.getVehicleSet().add(vehicle);
            vehicle.getVehicleOwners().add(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void removeVehicle(int id, Vehicles vehicle) {
        Allies ally = find(id);
        if(ally != null) {
            entityManager.getTransaction().begin();
            ally.getVehicleSet().remove(vehicle);
            vehicle.getVehicleOwners().remove(ally);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
