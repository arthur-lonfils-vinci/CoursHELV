package parallel;

import java.util.List;
import java.util.stream.IntStream;

import static parallel.DelayedOperations.runAndRecordTime;

public class ParallelStreams {

    //TODO: initialiser numbers grâce à un InStream pour obtenir une liste d'entier de 1 à 100
    //      Aidez-vous de la théorie p.14-15 "La génération d'un IntStream"
    private static List<Integer> numbers = IntStream.rangeClosed(0,100).boxed().toList();

    public static void main(String[] args) {
        System.out.println("Temps d'exécution en série : " + serialMap());
        System.out.println("Temps d'exécution en parallèle : " + parallelMap());
        System.out.printf("Temps d'exécution lorsque l'on filtre avant : \n     en série : %d\n     en parallèle : %d\n",
                serialFilteredBefore(), parallelFilteredBefore());
        System.out.printf("Temps d'exécution lorsque l'on filtre après : \n     en série : %d\n     en parallèle : %d\n",
                serialFilteredAfter(), parallelFilteredAfter());
        System.out.println("Temps d'exécution lorsque les multiple de 10 sont très lents : " + randomMap());
    }

    /**
     * Exécute les trois fonctions de multiplication de l'interface DelayedOperations sur un stream
     * l'une après l'autre et affiche le temps que ce processus à pris pour s'exécuter.
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution des 3 opérations.
     */
    private static long serialMap() {
        return runAndRecordTime( () -> {
            //TODO: 1. effecuter un map avec fastMult2
            //      2. effectuer un map avec slowMult2
            //      3. effectuer un map avec ultraSlowMult2
            System.out.println(numbers.stream().map(DelayedOperations::fastMult2).toList());
            System.out.println(numbers.stream().map(DelayedOperations::slowMult2).toList());
            System.out.println(numbers.stream().map(DelayedOperations::ultraSlowMult2).toList());
        });
    }

    /**
     * Exécute les trois fonctions de multiplication de l'interface DelayedOperations sur un stream
     * en parallèle et affiche le temps que ce processus à pris pour s'exécuter.
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution des 3 opérations.
     */
    private static long parallelMap() {
        //TODO: Inspirez-vous de serialMap pour retourner le temps d'exécution des trois
        //      opérations de mapping. Cette fois, elles doivent être exécutées en parallèle.
        //      L'opération parallel() permet de retourner un parallèle équivalent au stream original
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream().parallel().map(DelayedOperations::fastMult2).toList());
            System.out.println(numbers.stream().parallel().map(DelayedOperations::slowMult2).toList());
            System.out.println(numbers.stream().parallel().map(DelayedOperations::ultraSlowMult2).toList());
        });
    }

    /**
     * Exécute la fonction ultraSlowMult2 de DelayedOperations sur un stream filtré
     * et affiche le temps que ce processus à pris pour s'exécuter.
     *
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution
     */
    private static long serialFilteredBefore() {
        //TODO: mesurer le temps d'exécution du mapping de ulstraSlowMult2 sur le stream dont on ne garde que les élément pairs
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream().filter(e -> e%2 == 0).map(DelayedOperations::ultraSlowMult2).toList());
        });
    }

    /**
     * Exécute la fonction ultraSlowMult2 de DelayedOperations sur un stream, puis le filtre
     * et affiche le temps que ce processus à pris pour s'exécuter.
     * @return le temps d'exécution
     */
    private static long serialFilteredAfter() {
        //TODO: mesurer le temps d'exécution du mapping de ulstraSlowMult2 sur le stream, puis du filtre pair
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream().map(DelayedOperations::ultraSlowMult2).filter(e -> e%2 == 0).toList());
        });
    }

    /**
     * Exécute la fonction ultraSlowMult2 de DelayedOperations sur un stream parallèle filtré
     * et affiche le temps que ce processus à pris pour s'exécuter.
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution
     */
    private static long parallelFilteredBefore() {
        //TODO: mesurer le temps d'exécution du mapping de ulstraSlowMult2 sur un stream parallèle dont on ne garde que les élément pairs
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream()
                    .parallel()
                    .filter(e -> e%2 == 0)
                    .map(DelayedOperations::ultraSlowMult2)
                    .toList());
        });
    }

    /**
     * Exécute la fonction ultraSlowMult2 de DelayedOperations sur un stream parallèle, puis le filtre
     * et affiche le temps que ce processus à pris pour s'exécuter.
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution
     */
    private static long parallelFilteredAfter() {
        //TODO: mesurer le temps d'exécution du mapping de ulstraSlowMult2 sur le stream parallèle, puis du filtre pair
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream()
                    .parallel()
                    .map(DelayedOperations::ultraSlowMult2)
                    .filter(e -> e%2 == 0)
                    .toList());
        });
    }

    /**
     * Exécute la fonction randomlySlowMult2 de DelayedOperations sur un stream parallèle
     * et affiche le temps que ce processus à pris pour s'exécuter.
     * Il faut récupérer le résultat des opérations dans une liste.
     * @return le temps d'exécution
     */
    private static long randomMap() {
        //TODO: mesurer le temps d'exécution du mapping de randomlySlowMult2 sur le stream parallèle
        return runAndRecordTime( () -> {
            System.out.println(numbers.stream()
                    .parallel()
                    .map(DelayedOperations::randomlySlowMult2)
                    .toList());
        });
    }
}
