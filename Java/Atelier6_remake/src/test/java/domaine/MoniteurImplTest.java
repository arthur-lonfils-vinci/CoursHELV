package domaine;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Sport sportCompetent;
    private Moniteur moniteur;
    private Stage stageValide;

    @BeforeEach
    void setUp() {
        this.sportCompetent = new SportStub(true);
        this.moniteur = new MoniteurImpl("Jean");
        this.stageValide = new StageStub(8, sportCompetent, (Moniteur) null);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; ++i) {
            moniteur.ajouterStage(new StageStub(i, this.sportCompetent, (Moniteur) null));
        }
    }

    @DisplayName("TC1: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC1() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.ajouterStage(this.stageValide)),
                () -> Assertions.assertTrue(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(1, this.moniteur.nombreDeStages())
        );

    }

    @DisplayName("TC2: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC2() {
        this.amenerALEtat(1, this.moniteur);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.ajouterStage(this.stageValide)),
                () -> Assertions.assertTrue(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(2, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC3: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC3() {
        this.amenerALEtat(2, this.moniteur);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.ajouterStage(this.stageValide)),
                () -> Assertions.assertTrue(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(3, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC4: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC4() {
        this.amenerALEtat(3, this.moniteur);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.ajouterStage(this.stageValide)),
                () -> Assertions.assertTrue(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(4, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC5: Test de la méthode supprimerStage")
    @Test
    void testMoniteurTC5() {
        this.amenerALEtat(3, this.moniteur);
        this.moniteur.ajouterStage(this.stageValide);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.supprimerStage(this.stageValide)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(3, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC6: Test de la méthode supprimerStage")
    @Test
    void testMoniteurTC6() {
        this.amenerALEtat(2, this.moniteur);
        this.moniteur.ajouterStage(this.stageValide);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.supprimerStage(this.stageValide)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(2, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC7: Test de la méthode supprimerStage")
    @Test
    void testMoniteurTC7() {
        this.amenerALEtat(1, this.moniteur);
        this.moniteur.ajouterStage(this.stageValide);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.supprimerStage(this.stageValide)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(1, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC8: Test de la méthode supprimerStage")
    @Test
    void testMoniteurTC8() {
        this.moniteur.ajouterStage(this.stageValide);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.supprimerStage(this.stageValide)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(this.stageValide)),
                () -> Assertions.assertEquals(0, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC9: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC9() {
        this.amenerALEtat(4, this.moniteur);
        this.moniteur.ajouterStage(this.stageValide);
        Assertions.assertAll(
                () -> Assertions.assertFalse(this.moniteur.ajouterStage(this.stageValide)),
                () -> Assertions.assertEquals(5, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC10: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC10() {
        this.amenerALEtat(4, this.moniteur);
        Stage stageMemeSemaine = new StageStub(1, this.sportCompetent, (Moniteur)null);
        Assertions.assertAll(
                () -> Assertions.assertFalse(this.moniteur.ajouterStage(stageMemeSemaine)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(stageMemeSemaine)),
                () -> Assertions.assertEquals(4, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC11: Test de la méthode supprimerStage")
    @Test
    void testMoniteurTC11() {
        this.amenerALEtat(4, this.moniteur);
        Assertions.assertAll(
                () -> Assertions.assertFalse(this.moniteur.supprimerStage(this.stageValide)),
                () -> Assertions.assertEquals(4, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC12: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC12() {
        this.amenerALEtat(4, this.moniteur);
        Moniteur autreMoniteur = new MoniteurImpl("Paul");
        Stage stageAutreMoniteur = new StageStub(8, this.sportCompetent, autreMoniteur);
        Assertions.assertAll(
                () -> Assertions.assertFalse(this.moniteur.ajouterStage(stageAutreMoniteur)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(stageAutreMoniteur)),
                () -> Assertions.assertEquals(4, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC13: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC13() {
        this.amenerALEtat(4, this.moniteur);
        Stage stageBonMoniteur = new StageStub(8, this.sportCompetent, this.moniteur);
        Assertions.assertAll(
                () -> Assertions.assertTrue(this.moniteur.ajouterStage(stageBonMoniteur)),
                () -> Assertions.assertTrue(this.moniteur.contientStage(stageBonMoniteur)),
                () -> Assertions.assertEquals(5, this.moniteur.nombreDeStages())
        );
    }

    @DisplayName("TC14: Test de la méthode ajouterStage")
    @Test
    void testMoniteurTC14() {
        this.amenerALEtat(4, this.moniteur);
        Sport sportIncompetent = new SportStub(false);
        Stage stageMauvaisSport = new StageStub(8, sportIncompetent, null);

        Assertions.assertAll(
                () -> Assertions.assertFalse(this.moniteur.ajouterStage(stageMauvaisSport)),
                () -> Assertions.assertFalse(this.moniteur.contientStage(stageMauvaisSport)),
                () -> Assertions.assertEquals(4, this.moniteur.nombreDeStages())
        );
    }

}