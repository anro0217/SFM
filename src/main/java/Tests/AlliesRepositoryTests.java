package Tests;

import com.github.zdanielm.sfm_projekt.Repository.AlliesRepository;
import com.github.zdanielm.sfm_projekt.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlliesRepositoryTests {

    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private AlliesRepository alliesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(emf.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        alliesRepository = new AlliesRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Allies ally = new Allies();
        alliesRepository.add(ally);
        verify(entityManager).persist(ally);
    }
    @Test
    public void testFind() {
        int bat_id = 1;
        Allies ally = new Allies();
        when(entityManager.find(Allies.class, bat_id)).thenReturn(ally);

        Allies result = alliesRepository.find(bat_id);

        assertEquals(ally, result);
        verify(entityManager).find(Allies.class, bat_id);
    }
    @Test
    public void testUpdate() {
        Allies ally = new Allies();
        ally.setBat_id(1);
        Allies allyToUpdate = new Allies();

        when(entityManager.find(Allies.class, ally.getBat_id())).thenReturn(allyToUpdate);
        when(entityManager.getTransaction()).thenReturn(transaction);

        Allies result = alliesRepository.update(ally);

        verify(entityManager).find(Allies.class, ally.getBat_id());
        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(ally.getName(), result.getName());
        assertEquals(ally.isIs_available(), result.isIs_available());
        assertEquals(ally.isIs_metahuman(), result.isIs_metahuman());
        assertEquals(ally.getSpecial_ability(), result.getSpecial_ability());
    }
    @Test
    public void testDelete() {
        Allies ally = new Allies();
        ally.setBat_id(1);

        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.delete(ally);

        verify(transaction).begin();
        verify(entityManager).remove(ally);
        verify(transaction).commit();
    }
    @Test
    public void testAddCurrentMission() {
        int id = 1;
        Missions mission = new Missions();
        Allies ally = new Allies();

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.addCurrentMission(id, mission);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(mission.getMission_id(), ally.getCurrent_mission());
        assertTrue(mission.getHandledBy().contains(ally));
    }
    @Test
    public void testRemoveCurrentMission() {
        int id = 1;
        Missions mission = new Missions();
        mission.setMission_id(id);
        Allies ally = new Allies();
        ally.setCurrent_mission(mission);

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.removeCurrentMission(id, mission);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(0,ally.getCurrent_mission());
        assertFalse(mission.getHandledBy().contains(ally));
    }
    @Test
    public void testAddGothamKnight() {
        int id = 1;
        Gotham_knights gothamKnight = new Gotham_knights();
        Allies ally = new Allies();

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.addGothamKnight(id, gothamKnight);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(gothamKnight, ally.getGothamKnight());
        assertEquals(ally, gothamKnight.getAlly());
    }
    @Test
    public void testRemoveGothamKnight() {
        int id = 1;
        Gotham_knights gothamKnight = new Gotham_knights();
        Allies ally = new Allies();
        ally.setGothamKnight(gothamKnight);
        gothamKnight.setAlly(ally);

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.removeGothamKnight(id, gothamKnight);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertNull(ally.getGothamKnight());
        assertNull(gothamKnight.getAlly());
    }
    @Test
    public void testAddTool() {
        int id = 1;
        Tools tool = new Tools();
        Allies ally = new Allies();

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.addTool(id, tool);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(ally.getToolSet().contains(tool));
        assertTrue(tool.getToolOwners().contains(ally));
    }
    @Test
    public void testRemoveTool() {
        int id = 1;
        Tools tool = new Tools();
        Allies ally = new Allies();
        ally.getToolSet().add(tool);
        tool.getToolOwners().add(ally);

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.removeTool(id, tool);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(ally.getToolSet().contains(tool));
        assertFalse(tool.getToolOwners().contains(ally));
    }
    @Test
    public void testAddVehicle() {
        int id = 1;
        Vehicles vehicle = new Vehicles();
        Allies ally = new Allies();

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.addVehicle(id, vehicle);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(ally.getVehicleSet().contains(vehicle));
        assertTrue(vehicle.getVehicleOwners().contains(ally));
    }
    @Test
    public void testRemoveVehicle() {
        int id = 1;
        Vehicles vehicle = new Vehicles();
        Allies ally = new Allies();
        ally.getVehicleSet().add(vehicle);
        vehicle.getVehicleOwners().add(ally);

        when(entityManager.find(Allies.class, id)).thenReturn(ally);
        when(entityManager.getTransaction()).thenReturn(transaction);

        alliesRepository.removeVehicle(id, vehicle);

        verify(entityManager).find(Allies.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(ally.getVehicleSet().contains(vehicle));
        assertFalse(vehicle.getVehicleOwners().contains(ally));
    }
}