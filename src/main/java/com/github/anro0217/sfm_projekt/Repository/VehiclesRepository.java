package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Vehicles;

import javax.persistence.*;
// import javax.persistence.criteria.*;
import java.util.List;

public class VehiclesRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public VehiclesRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public VehiclesRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public VehiclesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Vehicles add(Vehicles verda) {
        entityManager.getTransaction().begin();
        entityManager.persist(verda);
        entityManager.getTransaction().commit();
        return verda;
    }
    public Vehicles find(int id) {
        return entityManager.find(Vehicles.class, id);
    }

    public Vehicles update(Vehicles verda) {
        Vehicles verdaToUpdate = find(verda.getId());
        entityManager.getTransaction().begin();
        verdaToUpdate.setName(verda.getName());
        entityManager.getTransaction().commit();
        return verdaToUpdate;
    }

    public void delete(Vehicles verda) {
        entityManager.getTransaction().begin();
        entityManager.remove(verda);
        entityManager.getTransaction().commit();
    }

    public void addAlly(int id, Allies ally) {
        Vehicles verda = find(id);
        if(verda != null) {
            entityManager.getTransaction().begin();
            verda.getVehicleOwners().add(ally);
            ally.getVehicleSet().add(verda);
            entityManager.getTransaction().commit();
        }
    }

    public void removeAlly(int id, Allies ally) {
        Vehicles verda = find(id);
        if(verda != null) {
            entityManager.getTransaction().begin();
            verda.getVehicleOwners().remove(ally);
            ally.getVehicleSet().remove(verda);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
