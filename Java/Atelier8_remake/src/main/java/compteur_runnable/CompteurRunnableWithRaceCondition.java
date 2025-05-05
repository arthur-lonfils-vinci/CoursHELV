package compteur_runnable;

import sync.Compteur;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CompteurRunnableWithRaceCondition extends Compteur implements Runnable {
    private static AtomicInteger ordreArrivee = new AtomicInteger(0);

    public CompteurRunnableWithRaceCondition(String nom, int max) {
        super(nom, max);
    }

    @Override
    public void run() {
        count();
    }

    @Override
    public void count() {
        IntStream.rangeClosed(1, getMax()).forEach(i -> {
            System.out.println(getNom() + " : " + i);
            try {
                Thread.sleep(10);
                if (i == getMax()) {
                    /* NB : code avant l'utilisation de AtomicInteger (ordreArrivee était un int)
                    ordreArrivee++;
                    Thread.sleep(10);  */
                    System.out.println(getNom() + " a finit de compter jusqu'à " + getMax() + " et a terminé en postion " + ordreArrivee.addAndGet(1));
                    Thread.sleep(10);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}
