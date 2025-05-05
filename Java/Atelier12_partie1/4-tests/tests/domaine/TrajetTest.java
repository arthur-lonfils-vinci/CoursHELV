package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrajetTest {
    private Caisse caisse;
    private Trajet trajet;

    @BeforeEach
    void setUp() {
        caisse = new Caisse("1", LocalDate.of(2025,01,31), "BXL", "Liege", 1000);
        trajet = new Trajet("1",LocalDate.of(2025,01,31), "BXL", "Liege");
    }

    @Test
    void peutAjouter() {
        assertThrows(IllegalArgumentException.class, () -> trajet.peutAjouter(null));
    }
    @Test
    void peutAjouter1() {
        Caisse caisse2 = new Caisse("1", LocalDate.of(2025,1,31), "BX", "Liege", 1000);
        assertFalse(trajet.peutAjouter(caisse2));
    }

    @Test
    void peutAjouter2() {
        assertTrue(trajet.peutAjouter(caisse));
    }
}