package parallel;

import java.time.Duration;
import java.time.Instant;

public interface DelayedOperations {

    private static Integer mult2(Integer value, long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return value * 2;
    }

    /**
     * Multiplie le paramètre par 2 après 1 milliseconde.
     * @param value un entier
     * @return l'entier multiplié par 2
     */
    static Integer fastMult2(Integer value) {
        return mult2(value, 1);
    }

    /**
     * Multiplie le paramètre par 2 après 10 milliseconde.
     * @param value un entier
     * @return l'entier multiplié par 2
     */
    static Integer slowMult2(Integer value) {
        return mult2(value, 10);
    }

    /**
     * Multiplie le paramètre par 2 après 100 millisecondes.
     * @param value un entier
     * @return l'entier multiplié par 2
     */
    static Integer ultraSlowMult2(Integer value) {
        return mult2(value, 100);
    }

    /**
     * Multiplie le paramètre par 2 après 500 millisecondes si c'est un multiple de 10,
     * et après 1 milliseconde sinon.
     * @param value un entier
     * @return l'entier multiplié par 2
     */
    static Integer randomlySlowMult2(Integer value) {
        return value % 10 == 0 ? mult2(value, 500) : fastMult2(value);
    }

    /**
     * Exécute le processus passé en paramètre et calcule le temps d'exécution, en millisecondes.
     * S'utilise au moyen d'une expression lambda :
     *      long l = runAndRecord(() -> {
     *          //operations
     *      });
     * @param process le processus à exécuter.
     * @return Le temps d'exécution du processus, en millisecondes
     */
    static long runAndRecordTime(Runnable process) {
        Instant start = Instant.now();
        process.run();
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }
}
