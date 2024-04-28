package com.github.zdanielm.sfm_projekt.Repository;

import com.github.zdanielm.sfm_projekt.model.ArkhamAsylum;
import com.github.zdanielm.sfm_projekt.model.Crimes;
import com.github.zdanielm.sfm_projekt.model.Missions;
import com.github.zdanielm.sfm_projekt.model.Villains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.TypedQuery;

public class VillainsRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public VillainsRepository() {
        this.emf = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public VillainsRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }
    public VillainsRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Villains add(Villains villain) {
        entityManager.getTransaction().begin();
        entityManager.persist(villain);
        entityManager.getTransaction().commit();
        return villain;
    }

    public Villains find(int villain_id) {
        return entityManager.find(Villains.class, villain_id);
    }

    public Villains findNickname(String nickname) {
        String queryString = "SELECT v FROM Villains v WHERE v.nickname = :nick";
        List<Villains> resultList = entityManager.createQuery(queryString, Villains.class)
                .setParameter("nick", nickname)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
    //ez minden db-beli nevet visszaad
    public List<String> findNames() {
        Query query = entityManager.createQuery("Select nickname from Villains");
        return query.getResultList();
    }

    //egy adott nickname-et
    public List<String> searchNicknames(String nick) {
        String sql = "SELECT nickname FROM Villains WHERE LOWER(nickname) LIKE ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, "%" + nick.toLowerCase() + "%");
        return query.getResultList();
    }

    //Ez megadott tulajdonságot keres, egy adott értékkel
    //value legyen String -> .toString()
    public List<String> searchByField(String field, String value) {
        String sql = "SELECT " + field + " FROM Villains WHERE LOWER(" + field + ") LIKE ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, "%" + value.toLowerCase() + "%");
        return query.getResultList();
    }
    public List<String> findCrimesByVillain(String villainNickname) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT c.crime FROM Crimes c " +
                        "JOIN c.committedBy v " +
                        "WHERE v.nickname = :villainNickname", String.class
        );

        query.setParameter("villainNickname", villainNickname);
        return query.getResultList();
    }
    // Ellenőrzi, hogy van-e már ilyen Nickname az adatbázisban
    public boolean isNicknameExists(String nickname) {
        List<String> nicknames = findNames();
        return nicknames.contains(nickname);
    }

    public Villains update(Villains villain) {
        Villains villainToUpdate = find(villain.getVillain_id());
        entityManager.getTransaction().begin();
        villainToUpdate.setName(villain.getName());
        villainToUpdate.setNickname(villain.getNickname());
        villainToUpdate.setBirthname(villain.getBirthname());
        villainToUpdate.setMothers_name(villain.getMothers_name());
        villainToUpdate.setDate_of_birth(villain.getDate_of_birth());
        villainToUpdate.setWeight(villain.getWeight());
        villainToUpdate.setHeight(villain.getHeight());
        villainToUpdate.setDanger_level(villain.getDanger_level());
        villainToUpdate.setIs_metahuman(villain.isIs_metahuman());
        villainToUpdate.setSpecial_ability(villain.getSpecial_ability());
        villainToUpdate.setWeakness(villain.getWeakness());
        villainToUpdate.setBlood_type(villain.getBlood_type());
        villainToUpdate.setNum_of_victims(villain.getNum_of_victims());
        villainToUpdate.setLocation(villain.getLocation());
        villainToUpdate.setIs_detained(villain.isIs_detained());
        villainToUpdate.setDescription(villain.getDescription());
        entityManager.getTransaction().commit();
        return villainToUpdate;
    }

    public void delete(Villains villain) {
        entityManager.getTransaction().begin();
        entityManager.remove(villain);
        entityManager.getTransaction().commit();
    }

    public void addCurrentAffair(int id, Missions mission) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.setCurrentAffair(mission);
            mission.getCurrentEnemies().add(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void removeCurrentAffair(int id, Missions mission) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.setCurrentAffair(null);
            mission.getCurrentEnemies().remove(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void addTreatedAt(int id, ArkhamAsylum asylum) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.setTreatedAt(asylum);
            asylum.getPatients().add(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void removeTreatedAt(int id, ArkhamAsylum asylum) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.setTreatedAt(null);
            asylum.getPatients().remove(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void addCrime(int id, Crimes crime) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.getCommittedCrimes().add(crime);
            crime.getCommittedBy().add(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void removeCrime(int id, Crimes crime) {
        Villains villain = find(id);
        if(villain != null) {
            entityManager.getTransaction().begin();
            villain.getCommittedCrimes().remove(crime);
            crime.getCommittedBy().remove(villain);
            entityManager.getTransaction().begin();
        }
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
