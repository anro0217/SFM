package Tests;

import com.github.zdanielm.sfm_projekt.Repository.CrimesRepository;
import com.github.zdanielm.sfm_projekt.model.Crimes;
import com.github.zdanielm.sfm_projekt.model.Villains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrimesRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private CrimesRepository crimesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        crimesRepository = new CrimesRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Crimes crime = new Crimes();

        Crimes result = crimesRepository.add(crime);

        verify(transaction).begin();
        verify(entityManager).persist(crime);
        verify(transaction).commit();
        assertEquals(crime, result);
    }

    @Test
    public void testFind() {
        int crime_id = 1;
        Crimes crime = new Crimes();

        when(entityManager.find(Crimes.class, crime_id)).thenReturn(crime);

        Crimes result = crimesRepository.find(crime_id);

        verify(entityManager).find(Crimes.class, crime_id);
        assertEquals(crime, result);
    }

    @Test
    public void testUpdate() {
        Crimes crime = new Crimes();
        crime.setCrimeId(1);

        when(entityManager.find(Crimes.class, crime.getCrimeId())).thenReturn(crime);

        Crimes result = crimesRepository.update(crime);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(crime, result);
    }

    @Test
    public void testDelete() {
        Crimes crime = new Crimes();

        crimesRepository.delete(crime);

        verify(transaction).begin();
        verify(entityManager).remove(crime);
        verify(transaction).commit();
    }
    @Test
    public void testAddVillain() {
        int crime_id = 1;
        Villains villain = new Villains();
        Crimes crime = new Crimes();

        when(entityManager.find(Crimes.class, crime_id)).thenReturn(crime);
        when(entityManager.getTransaction()).thenReturn(transaction);

        crimesRepository.addVillain(crime_id, villain);

        verify(entityManager).find(Crimes.class, crime_id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(crime.getCommittedBy().contains(villain));
        assertTrue(villain.getCommittedCrimes().contains(crime));
    }
    @Test
    public void testRemoveVillain() {
        int crime_id = 1;
        Villains villain = new Villains();
        Crimes crime = new Crimes();
        crime.getCommittedBy().add(villain);
        villain.getCommittedCrimes().add(crime);

        when(entityManager.find(Crimes.class, crime_id)).thenReturn(crime);
        when(entityManager.getTransaction()).thenReturn(transaction);

        crimesRepository.removeVillain(crime_id, villain);

        verify(entityManager).find(Crimes.class, crime_id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(crime.getCommittedBy().contains(villain));
        assertFalse(villain.getCommittedCrimes().contains(crime));
    }
}