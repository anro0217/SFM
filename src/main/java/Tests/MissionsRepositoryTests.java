package Tests;

import com.github.zdanielm.sfm_projekt.Repository.MissionsRepository;
import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Missions;
import com.github.zdanielm.sfm_projekt.model.Villains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MissionsRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private MissionsRepository missionsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        missionsRepository = new MissionsRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Missions mission = new Missions();

        Missions result = missionsRepository.add(mission);

        verify(transaction).begin();
        verify(entityManager).persist(mission);
        verify(transaction).commit();
        assertEquals(mission, result);
    }

    @Test
    public void testFind() {
        int mission_id = 1;
        Missions mission = new Missions();

        when(entityManager.find(Missions.class, mission_id)).thenReturn(mission);

        Missions result = missionsRepository.find(mission_id);

        verify(entityManager).find(Missions.class, mission_id);
        assertEquals(mission, result);
    }

    @Test
    public void testUpdate() {
        Missions mission = new Missions();
        mission.setMission_id(1);

        when(entityManager.find(Missions.class, mission.getMission_id())).thenReturn(mission);

        Missions result = missionsRepository.update(mission);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(mission, result);
    }
    @Test
    public void testAddAlly() {
        int id = 1;
        Allies ally = new Allies();
        Missions mission = new Missions();

        when(entityManager.find(Missions.class, id)).thenReturn(mission);
        when(entityManager.getTransaction()).thenReturn(transaction);

        missionsRepository.addAlly(id, ally);

        verify(entityManager).find(Missions.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(mission.getHandledBy().contains(ally));
        assertEquals(mission.getMission_id(), ally.getCurrent_mission());
    }
    @Test
    public void testRemoveAlly() {
        int id = 1;
        Allies ally = new Allies();
        Missions mission = new Missions();
        mission.getHandledBy().add(ally);
        ally.setCurrent_mission(mission);

        when(entityManager.find(Missions.class, id)).thenReturn(mission);
        when(entityManager.getTransaction()).thenReturn(transaction);

        missionsRepository.removeAlly(id, ally);

        verify(entityManager).find(Missions.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(mission.getHandledBy().contains(ally));
        assertEquals(0,ally.getCurrent_mission());
    }
    @Test
    public void testAddVillain() {
        int id = 1;
        Villains villain = new Villains();
        Missions mission = new Missions();

        when(entityManager.find(Missions.class, id)).thenReturn(mission);
        when(entityManager.getTransaction()).thenReturn(transaction);

        missionsRepository.addVillain(id, villain);

        verify(entityManager).find(Missions.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(mission.getCurrentEnemies().contains(villain));
        assertEquals(mission, villain.getCurrentAffair());
    }
    @Test
    public void testRemoveVillain() {
        int id = 1;
        Villains villain = new Villains();
        Missions mission = new Missions();
        mission.getCurrentEnemies().add(villain);
        villain.setCurrentAffair(mission);

        when(entityManager.find(Missions.class, id)).thenReturn(mission);
        when(entityManager.getTransaction()).thenReturn(transaction);

        missionsRepository.removeVillain(id, villain);

        verify(entityManager).find(Missions.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(mission.getCurrentEnemies().contains(villain));
        assertNull(villain.getCurrentAffair());
    }
}