package be.vinci.aj;

import be.vinci.aj.domain.Locomotive;
import be.vinci.aj.domain.Train;
import be.vinci.aj.domain.TrainImpl;
import be.vinci.aj.domain.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainTest {
    Locomotive locomotive;
    Train train;
    Wagon wagon;
    Wagon wagon2;

    @BeforeEach
    void setUp() {
        locomotive = mock(Locomotive.class);
        train = new TrainImpl("Bruxelles", "Paris", 150);
        wagon = mock(Wagon.class);
        wagon2 = mock(Wagon.class);
    }

    @Test
    @DisplayName("TC1 Add Locomotive")
    void ajouterVehicule() {
        when(locomotive.getPoids()).thenReturn(2000);
        when(locomotive.getPuissance()).thenReturn(6000);

        assertTrue(train.ajouterVehicule(locomotive));
        assertEquals(locomotive, train.getVehicules().get(0));

        verify(locomotive).getPoids();
        verify(locomotive).getPuissance();
    }

    @Test
    @DisplayName("TC2 add Wagon")
    void ajouterWagon() {
        when(locomotive.getPoids()).thenReturn(2000);
        when(locomotive.getPuissance()).thenReturn(6000);

        train.ajouterVehicule(locomotive);

        when(wagon.getPoids()).thenReturn(2000);

        assertTrue(train.ajouterVehicule(wagon));
        assertEquals(wagon, train.getVehicules().get(1));

        verify(wagon).getPoids();
    }

    @Test
    @DisplayName("TC 3 add a second wagon")
    void ajouterWagon2() {
        when(locomotive.getPoids()).thenReturn(2000);
        when(locomotive.getPuissance()).thenReturn(6000);
        train.ajouterVehicule(locomotive);
        when(wagon.getPoids()).thenReturn(2000);
        train.ajouterVehicule(wagon);
        when(wagon2.getPoids()).thenReturn(3000);

        assertFalse(train.ajouterVehicule(wagon2));

        verify(wagon2).getPoids();
    }

    @Test
    @DisplayName("TC4 add locomotive with power < weight thr Exception")
    void ajouterLocomotive() {
        when(locomotive.getPoids()).thenReturn(2000);
        when(locomotive.getPuissance()).thenReturn(1000);

        assertThrows(IllegalArgumentException.class, () -> {train.ajouterVehicule(locomotive);});
    }
}