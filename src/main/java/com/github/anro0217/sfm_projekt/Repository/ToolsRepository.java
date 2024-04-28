package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Tools;

import javax.persistence.*;
// import javax.persistence.criteria.*;
import java.util.List;

public class ToolsRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ToolsRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public ToolsRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public ToolsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Tools add(Tools tool) {
        entityManager.getTransaction().begin();
        entityManager.persist(tool);
        entityManager.getTransaction().commit();
        return tool;
    }

    public Tools find(int id) {
        return entityManager.find(Tools.class, id);
    }

    public Tools update(Tools tool) {
        Tools toolToUpdate = find(tool.getId());
        entityManager.getTransaction().begin();
        toolToUpdate.setName(tool.getName());
        entityManager.getTransaction().commit();
        return toolToUpdate;
    }

    public void delete(Tools tool) {
        entityManager.getTransaction().begin();
        entityManager.remove(tool);
        entityManager.getTransaction().commit();
    }

    public void addAlly(int id, Allies ally) {
        Tools tool = find(id);
        if(tool != null) {
            entityManager.getTransaction().begin();
            tool.getToolOwners().add(ally);
            ally.getToolSet().add(tool);
            entityManager.getTransaction().commit();
        }
    }

    public void removeAlly(int id, Allies ally) {
        Tools tool = find(id);
        if(tool != null) {
            entityManager.getTransaction().begin();
            tool.getToolOwners().remove(ally);
            ally.getToolSet().remove(tool);
            entityManager.getTransaction().commit();
        }
    }
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
