package Tests;

import com.github.zdanielm.sfm_projekt.Repository.ToolsRepository;
import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Tools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ToolsRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private ToolsRepository toolsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        toolsRepository = new ToolsRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Tools tool = new Tools();

        toolsRepository.add(tool);

        verify(transaction).begin();
        verify(entityManager).persist(tool);
        verify(transaction).commit();
    }
    @Test
    public void testFind() {
        int id = 1;
        Tools tool = new Tools();

        when(entityManager.find(Tools.class, id)).thenReturn(tool);

        Tools result = toolsRepository.find(id);

        verify(entityManager).find(Tools.class, id);
        assertEquals(tool, result);
    }
    @Test
    public void testUpdate() {
        Tools tool = new Tools();
        tool.setId(1);
        tool.setName("New Tool Name");

        when(entityManager.getTransaction()).thenReturn(transaction);
        when(toolsRepository.find(tool.getId())).thenReturn(tool);

        Tools result = toolsRepository.update(tool);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(tool, result);
    }
    @Test
    public void testDelete() {
        Tools tool = new Tools();

        when(entityManager.getTransaction()).thenReturn(transaction);

        toolsRepository.delete(tool);

        verify(transaction).begin();
        verify(entityManager).remove(tool);
        verify(transaction).commit();
    }
    @Test
    public void testAddAlly() {
        int id = 1;
        Allies ally = new Allies();
        Tools tool = new Tools();

        when(entityManager.getTransaction()).thenReturn(transaction);
        when(toolsRepository.find(id)).thenReturn(tool);

        toolsRepository.addAlly(id, ally);

        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(tool.getToolOwners().contains(ally));
        assertTrue(ally.getToolSet().contains(tool));
    }

}