package compteur_runnable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCompteurRunnableWithRaceCondition {
    public static void main(String[] args) {
        List<CompteurRunnableWithRaceCondition> compteurs = new ArrayList<>();
        compteurs.add(new CompteurRunnableWithRaceCondition("Bolt", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Jakson", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Robert", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Stéphanie", 10));
        LocalDateTime start = LocalDateTime.now();

        for (CompteurRunnableWithRaceCondition compteurRunnableWithRaceCondition : compteurs) {
            Thread t = new Thread(compteurRunnableWithRaceCondition);
            t.start();
        }
        LocalDateTime end = LocalDateTime.now();
        long duration = java.time.Duration.between(start, end).toMillis();
        System.out.println("Durée avant d'atteindre cette instruction de fin du programme principal : " + duration + " ms");
    }

}
