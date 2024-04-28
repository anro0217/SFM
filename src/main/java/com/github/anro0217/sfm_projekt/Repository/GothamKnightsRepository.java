package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Gotham_knights;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GothamKnightsRepository {

    private EntityManagerFactory emf;
    private EntityManager entityManager;


    public GothamKnightsRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public GothamKnightsRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public GothamKnightsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Gotham_knights add(Gotham_knights gotham_knight) {
        entityManager.getTransaction().begin();
        entityManager.persist(gotham_knight);
        entityManager.getTransaction().commit();
        return gotham_knight;
    }

    public Gotham_knights find(int bat_id) {
        return entityManager.find(Gotham_knights.class, bat_id);
    }

    public Gotham_knights update(Gotham_knights gotham_knight) {
        Gotham_knights knightToUpdate = find(gotham_knight.getBat_id());
        entityManager.getTransaction().begin();
        knightToUpdate.setUsername(gotham_knight.getUsername());
        knightToUpdate.setGname(gotham_knight.getGName());
        knightToUpdate.setAccess_rights(gotham_knight.getAccess_rights());
        knightToUpdate.setPassword(gotham_knight.getPassword());
        entityManager.getTransaction().commit();
        return knightToUpdate;
    }

    public void delete(Gotham_knights gotham_knight) {
        entityManager.getTransaction().begin();
        entityManager.remove(gotham_knight);
        entityManager.getTransaction().commit();
    }

    public void addAlly(int id, Allies ally) {
        Gotham_knights gothamKnight = find(id);
        if(gothamKnight != null) {
            entityManager.getTransaction().begin();
            gothamKnight.setAlly(ally);
            ally.setGothamKnight(gothamKnight);
            entityManager.getTransaction().commit();
        }
    }

    public void removeAlly(int id, Allies ally) {
        Gotham_knights gothamKnight = find(id);
        if(gothamKnight != null) {
            entityManager.getTransaction().begin();
            gothamKnight.setAlly(null);
            ally.setGothamKnight(null);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
