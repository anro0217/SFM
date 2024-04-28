package Tests;

import com.github.zdanielm.sfm_projekt.Repository.ArkhamAsylumRepository;
import com.github.zdanielm.sfm_projekt.model.ArkhamAsylum;
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

public class ArkhamAsylumRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private ArkhamAsylumRepository arkhamAsylumRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        arkhamAsylumRepository = new ArkhamAsylumRepository(entityManager);
    }

    @Test
    public void testAdd() {
        ArkhamAsylum asylum = new ArkhamAsylum();

        ArkhamAsylum result = arkhamAsylumRepository.add(asylum);

        verify(transaction).begin();
        verify(entityManager).persist(asylum);
        verify(transaction).commit();
        assertEquals(asylum, result);
    }

    @Test
    public void testFind() {
        int villain_id = 1;
        ArkhamAsylum asylum = new ArkhamAsylum();

        when(entityManager.find(ArkhamAsylum.class, villain_id)).thenReturn(asylum);

        ArkhamAsylum result = arkhamAsylumRepository.find(villain_id);

        verify(entityManager).find(ArkhamAsylum.class, villain_id);
        assertEquals(asylum, result);
    }

    @Test
    public void testUpdate() {
        ArkhamAsylum asylum = new ArkhamAsylum();
        asylum.setSectionId(1);

        when(entityManager.find(ArkhamAsylum.class, asylum.getSectionId())).thenReturn(asylum);

        ArkhamAsylum result = arkhamAsylumRepository.update(asylum);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(asylum, result);
    }
    @Test
    public void testDelete() {
        ArkhamAsylum asylum = new ArkhamAsylum();

        arkhamAsylumRepository.delete(asylum);

        verify(transaction).begin();
        verify(entityManager).remove(asylum);
        verify(transaction).commit();
    }
    @Test
    public void testAddPatient() {
        int id = 1;
        Villains villain = new Villains();
        ArkhamAsylum asylum = new ArkhamAsylum();

        when(entityManager.find(ArkhamAsylum.class, id)).thenReturn(asylum);
        when(entityManager.getTransaction()).thenReturn(transaction);

        arkhamAsylumRepository.addPatient(id, villain);

        verify(entityManager).find(ArkhamAsylum.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(asylum.getPatients().contains(villain));
        assertEquals(asylum, villain.getTreatedAt());
    }
    @Test
    public void testRemovePatient() {
        int id = 1;
        Villains villain = new Villains();
        ArkhamAsylum asylum = new ArkhamAsylum();
        asylum.getPatients().add(villain);
        villain.setCurrentAffair(new Missions());

        when(entityManager.find(ArkhamAsylum.class, id)).thenReturn(asylum);
        when(entityManager.getTransaction()).thenReturn(transaction);

        arkhamAsylumRepository.removePatient(id, villain);

        verify(entityManager).find(ArkhamAsylum.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(asylum.getPatients().contains(villain));
        assertNull(villain.getCurrentAffair());
    }

}