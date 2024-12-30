package compteur_runnable;

import java.util.ArrayList;
import java.util.List;

public class TestCompteurRunnableWithRaceCondition {
    public static void main(String[] args) {
        List<CompteurRunnableWithRaceCondition> compteurs = new ArrayList<>();
        Long start = System.currentTimeMillis();

        compteurs.add(new CompteurRunnableWithRaceCondition("Bolt", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Jakson", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Robert", 10));
        compteurs.add(new CompteurRunnableWithRaceCondition("Stéphanie", 10));

        List<Thread> threads = new ArrayList<>();
        for (CompteurRunnableWithRaceCondition compteurRunnableWithRaceCondition : compteurs) {
            Thread t = new Thread(compteurRunnableWithRaceCondition);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Durée avant d'atteindre cette instruction de fin du programme principal : " + duration + " ms");
    }

}
