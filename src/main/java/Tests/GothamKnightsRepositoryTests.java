package Tests;

import com.github.zdanielm.sfm_projekt.Repository.GothamKnightsRepository;
import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Gotham_knights;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GothamKnightsRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private GothamKnightsRepository gothamKnightsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        gothamKnightsRepository = new GothamKnightsRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Gotham_knights knight = new Gotham_knights();

        Gotham_knights result = gothamKnightsRepository.add(knight);

        verify(transaction).begin();
        verify(entityManager).persist(knight);
        verify(transaction).commit();
        assertEquals(knight, result);
    }

    @Test
    public void testFind() {
        int bat_id = 1;
        Gotham_knights knight = new Gotham_knights();

        when(entityManager.find(Gotham_knights.class, bat_id)).thenReturn(knight);

        Gotham_knights result = gothamKnightsRepository.find(bat_id);

        verify(entityManager).find(Gotham_knights.class, bat_id);
        assertEquals(knight, result);
    }

    @Test
    public void testUpdate() {
        Gotham_knights knight = new Gotham_knights();
        knight.setBat_id(1);

        when(entityManager.find(Gotham_knights.class, knight.getBat_id())).thenReturn(knight);

        Gotham_knights result = gothamKnightsRepository.update(knight);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(knight, result);
    }
    @Test
    public void testAddAlly() {
        int id = 1;
        Allies ally = new Allies();
        Gotham_knights gothamKnight = new Gotham_knights();

        when(entityManager.find(Gotham_knights.class, id)).thenReturn(gothamKnight);
        when(entityManager.getTransaction()).thenReturn(transaction);

        gothamKnightsRepository.addAlly(id, ally);

        verify(entityManager).find(Gotham_knights.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(ally, gothamKnight.getAlly());
        assertEquals(gothamKnight, ally.getGothamKnight());
    }
    @Test
    public void testRemoveAlly() {
        int id = 1;
        Allies ally = new Allies();
        Gotham_knights gothamKnight = new Gotham_knights();
        gothamKnight.setAlly(ally);
        ally.setGothamKnight(gothamKnight);

        when(entityManager.find(Gotham_knights.class, id)).thenReturn(gothamKnight);
        when(entityManager.getTransaction()).thenReturn(transaction);

        gothamKnightsRepository.removeAlly(id, ally);

        verify(entityManager).find(Gotham_knights.class, id);
        verify(transaction).begin();
        verify(transaction).commit();
        assertNull(gothamKnight.getAlly());
        assertNull(ally.getGothamKnight());
    }
}