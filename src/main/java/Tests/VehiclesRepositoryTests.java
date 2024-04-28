package Tests;

import com.github.zdanielm.sfm_projekt.Repository.VehiclesRepository;
import com.github.zdanielm.sfm_projekt.model.Allies;
import com.github.zdanielm.sfm_projekt.model.Vehicles;
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

public class VehiclesRepositoryTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private VehiclesRepository vehiclesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        vehiclesRepository = new VehiclesRepository(entityManager);
    }

    @Test
    public void testAdd() {
        Vehicles vehicle = new Vehicles();

        Vehicles result = vehiclesRepository.add(vehicle);

        verify(transaction).begin();
        verify(entityManager).persist(vehicle);
        verify(transaction).commit();
        assertEquals(vehicle, result);
    }

    @Test
    public void testFind() {
        int id = 1;
        Vehicles vehicle = new Vehicles();

        when(entityManager.find(Vehicles.class, id)).thenReturn(vehicle);

        Vehicles result = vehiclesRepository.find(id);

        verify(entityManager).find(Vehicles.class, id);
        assertEquals(vehicle, result);
    }

    @Test
    public void testUpdate() {
        Vehicles vehicle = new Vehicles();
        vehicle.setId(1);
        vehicle.setName("New Vehicle Name");

        when(entityManager.find(Vehicles.class, vehicle.getId())).thenReturn(vehicle);

        Vehicles result = vehiclesRepository.update(vehicle);

        verify(transaction).begin();
        verify(transaction).commit();
        assertEquals(vehicle, result);
    }
    @Test
    public void testDelete() {
        Vehicles vehicle = new Vehicles();

        vehiclesRepository.delete(vehicle);

        verify(transaction).begin();
        verify(entityManager).remove(vehicle);
        verify(transaction).commit();
    }
    @Test
    public void testAddAlly() {
        int id = 1;
        Allies ally = new Allies();
        Vehicles vehicle = new Vehicles();

        when(entityManager.getTransaction()).thenReturn(transaction);
        when(vehiclesRepository.find(id)).thenReturn(vehicle);

        vehiclesRepository.addAlly(id, ally);

        verify(transaction).begin();
        verify(transaction).commit();
        assertTrue(vehicle.getVehicleOwners().contains(ally));
        assertTrue(ally.getVehicleSet().contains(vehicle));
    }

    @Test
    public void testRemoveAlly() {
        int id = 1;
        Allies ally = new Allies();
        Vehicles vehicle = new Vehicles();
        vehicle.getVehicleOwners().add(ally);
        ally.getVehicleSet().add(vehicle);

        when(entityManager.getTransaction()).thenReturn(transaction);
        when(vehiclesRepository.find(id)).thenReturn(vehicle);

        vehiclesRepository.removeAlly(id, ally);

        verify(transaction).begin();
        verify(transaction).commit();
        assertFalse(vehicle.getVehicleOwners().contains(ally));
        assertFalse(ally.getVehicleSet().contains(vehicle));
    }
}