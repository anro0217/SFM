package Tests;

import com.github.zdanielm.sfm_projekt.Repository.VillainsRepository;
import com.github.zdanielm.sfm_projekt.model.ArkhamAsylum;
import com.github.zdanielm.sfm_projekt.model.Crimes;
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

public class VillainsRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private VillainsRepository villainsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        villainsRepository = new VillainsRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Villains villain = new Villains();

        Villains result = villainsRepository.add(villain);

        verify(transaction).begin();
        verify(entityManager).persist(villain);
        verify(transaction).commit();
        assertEquals(villain, result);
    }

    @Test
    public void testFind() {
        int id = 1;
        Villains villain = new Villains();

        when(entityManager.find(Villains.class, id)).thenReturn(villain);

        Villains result = villainsRepository.find(id);

        verify(entityManager).find(Villains.class, id);
        assertEquals(villain, result);
    }

    @Test
    public void testUpdate() {
        Villains villain = new Villains();
        villain.setVillain_id(1);
        villain.setName("New Villain Name");

        when(entityManager.find(Villains.class, villain.getVillain_id())).thenReturn(villain);

        Villains result = villainsRepository.update(villain);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(villain, result);
    }

    @Test
    public void testDelete() {
        Villains villain = new Villains();

        villainsRepository.delete(villain);

        verify(transaction).begin();
        verify(entityManager).remove(villain);
        verify(transaction).commit();
    }
    @Test
    public void testRemoveCurrentAffair() {
        int id = 1;
        Missions mission = new Missions();
        Villains villain = new Villains();
        villain.setCurrentAffair(mission);
        mission.getCurrentEnemies().add(villain);

        when(villainsRepository.find(id)).thenReturn(villain);

        villainsRepository.removeCurrentAffair(id, mission);

        verify(transaction).begin();
        assertNull(villain.getCurrentAffair());
        assertFalse(mission.getCurrentEnemies().contains(villain));
        verify(transaction).commit();
    }

    @Test
    public void testAddTreatedAt() {
        int id = 1;
        ArkhamAsylum asylum = new ArkhamAsylum();
        Villains villain = new Villains();

        when(villainsRepository.find(id)).thenReturn(villain);

        villainsRepository.addTreatedAt(id, asylum);

        verify(transaction).begin();
        assertEquals(asylum, villain.getTreatedAt());
        assertTrue(asylum.getPatients().contains(villain));
        verify(transaction).commit();
    }

    @Test
    public void testRemoveTreatedAt() {
        int id = 1;
        ArkhamAsylum asylum = new ArkhamAsylum();
        Villains villain = new Villains();
        villain.setTreatedAt(asylum);
        asylum.getPatients().add(villain);

        when(villainsRepository.find(id)).thenReturn(villain);

        villainsRepository.removeTreatedAt(id, asylum);

        verify(transaction).begin();
        assertNull(villain.getTreatedAt());
        assertFalse(asylum.getPatients().contains(villain));
        verify(transaction).commit();
    }
    @Test
    public void testAddCrime() {
        int id = 1;
        Crimes crime = new Crimes();
        Villains villain = new Villains();

        when(villainsRepository.find(id)).thenReturn(villain);

        villainsRepository.addCrime(id, crime);

        verify(transaction).begin();
        assertTrue(villain.getCommittedCrimes().contains(crime));
        assertTrue(crime.getCommittedBy().contains(villain));
        verify(transaction).commit();
    }

    @Test
    public void testRemoveCrime() {
        int id = 1;
        Crimes crime = new Crimes();
        Villains villain = new Villains();
        villain.getCommittedCrimes().add(crime);
        crime.getCommittedBy().add(villain);

        when(villainsRepository.find(id)).thenReturn(villain);

        villainsRepository.removeCrime(id, crime);

        verify(transaction).begin();
        assertFalse(villain.getCommittedCrimes().contains(crime));
        assertFalse(crime.getCommittedBy().contains(villain));
        verify(transaction).commit();
    }
}