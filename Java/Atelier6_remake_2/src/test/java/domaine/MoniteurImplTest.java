package domaine;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class MoniteurImplTest {
    private Sport sportCompetent;
    private Moniteur moniteur;
    private Stage stageValide;


    @BeforeEach
    void setUp() {
        sportCompetent = new SportStub(true);
        moniteur = new MoniteurImpl("Lagaffe");
        stageValide = new StageStub(1, sportCompetent, null);
    }

    @Test
    @DisplayName("TC1: ajouterStage() -> stage valide")
    void ajouterStageTC1() {
        assertTrue(moniteur.ajouterStage(stageValide));
    }

    @Test
    @DisplayName("TC14: ajouterStage() -> stage sans moniteur pour un sport pour lequel le moniteur n’est pas compétent")
    void ajouterStageTC14() {
        amenerALEtat(4, moniteur);
        Sport sportNonCompetent = new SportStub(false);
        Stage stageNonCompetent = new StageStub(5, sportNonCompetent, null);

        assertFalse(moniteur.ajouterStage(stageNonCompetent));
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            moniteur.ajouterStage(new StageStub(i, sportCompetent, null));
        }
    }
}