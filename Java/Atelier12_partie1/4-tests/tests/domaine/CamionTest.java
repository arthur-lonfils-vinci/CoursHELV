package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CamionTest {
    private Camion camion;
    private Trajet trajet;

    @BeforeEach
    void setUp() {
        camion = new Camion("Q-123-DFF", 20, 16400);
        trajet = mock(Trajet.class);
    }

    @Test
    void ajouterTrajet() {
        when(trajet.getDate()).thenReturn(LocalDate.now().plusDays(1));
        when(trajet.nbCaisses()).thenReturn(21);

        assertFalse(camion.ajouterTrajet(trajet));

        verify(trajet).getDate();
        verify(trajet).nbCaisses();
    }
}