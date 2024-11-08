package employee;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Bidi;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeManagement {

    /**
     * Un Supplier est un objets qui fournit des résultats. Appeler sa méthode get()
     * vous fournira le type passé en paramètre, ici un Stream de String.
     * Complétez la fonction pour créer le Stream à partir du fichier streamingvf.cvs
     */
    private static final Supplier<Stream<String>> supplier = () -> {
        //TODO: retourner un stream créer à partir du fichier. Aidez vous de la p.15 : "Créer des streams"
        //      En cas d'IOException, vous devez lancer une UncheckedIOException

        try {
            return Files.lines(Paths.get("./resources/streamingvf.csv"), Charset.defaultCharset());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    };

    public static void main(String[] args) {

        System.out.println("1. Première ligne du fichier : [" + firstLine() + "]");
        System.out.println("\n3. Noms de famille de plus de 8 lettres contenant 'K' ou 'O' :\n" + filteredLastnames());
        System.out.println("\n4. Liste des comptes occurences de 'e' :\n" + occurencesOfE());
        System.out.println("\n5. Tous les emails se terminent-ils par 'streamingvf.be' ? :" + allEmailCorrect());
        System.out.println("\n6. Prénom d'un employé au log nom de famille : " + longLastName());
        System.out.println("\n7. Nombre d'employés à mi-temps : " + numbreOfPartTimers());
        System.out.println("\n8. Ids selon plein temps/mi-temps :");
        timeDistrubution().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.print("\n9. Plus long nom du fichier : ");
        printLongestName();

    }

    /**
     * Utilise le Supplier pour retourner la première ligne du fichier streamingvf.csv
     * N'oubliez pas la gestion des exceptions grâce au try-with-resources
     * @return une String contenant la première ligne du fichier
     */
    private static String firstLine() {
        //TODO
        try(Stream<String> lines = supplier.get()) {
            return lines.findFirst().get();
        } catch(UncheckedIOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * Filtre le stream pour retourner une liste des noms de famille (lastname) de plus de 8 lettres
     * qui contiennent la lettre 'O' ou la lettre 'K'
     * @return une liste de String de plus de 8 lettres contenant 'O' ou 'K'
     */
    private static List<String> filteredLastnames() {
        Predicate<String> lenghtPredicate = s -> s.length() > 8;
        Predicate<String> containsOPredicate = s -> s.indexOf('O') > -1;
        Predicate<String> containsKPredicate = s -> s.indexOf('0') > -1;
        //TODO: combiner predicate avec d'autres  (p.7 : "Predicate"), puis le passer en paramètre de
        //      de l'appel filter() pour filtrer les résultats.

        Predicate<String> combinePredicate = lenghtPredicate.and(containsOPredicate).or(containsKPredicate);
        List<String> stream = supplier.get().map(Employee::new)
                .map(Employee::getLastname)
                .filter(combinePredicate)
                .collect(Collectors.toList());
        return stream;
    }

    /**
     * Calcule les occurrences du carcatère 'e' dans les prénoms des employés
     * @return une liste contenant le nombre d'occurrences de 'e' pour chaque prénom d'employé.
     */
    private static List<Integer> occurencesOfE() {
        //TODO: Construire une BiFunction qui prend comme premier type de paramètre un Employee,
        //      comme deuxième type de paramètre un Character, et qui prend Integer comme type de retour.
        //      Cette BiFunction doit retourner le nombre d'occurrences du char passé en paramètre dans le
        //      prénom (firstname) de l'Employee passé en paramètre.
        //      Retourner une liste contenant le nombre d'occurences du caractère 'e' dans les
        //      prénoms de tous les employés en utilisant votre BiFunction.

        BiFunction<Employee, Character, Integer> function = (e,c) -> (int) e.getFirstname().chars().filter(ch -> ch == c).count();

        return supplier.get().map(Employee::new).map(e -> function.apply(e,'e'))
                .collect(Collectors.toList());
    }

    /**
     * Vérifie que tous les emails des employés se terminent par "streamingvf.be"
     * @return true si tous les emails se terminent par "streamingvf.be", false sinon
     */
    private static boolean allEmailCorrect() {
        //TODO
        return supplier.get().map(Employee::new).map(Employee::getEmail).allMatch(e -> e.endsWith("@streamingvf.be"));
    }

    /**
     * Retourne le prénom d'un employé dont le nom de famille a une longueur supérieure à 14,
     * ou une String contenant "None" si aucun ne correspond.
     * @return une String contenant un prénom ou "None"
     */
    private static String longLastName() {
        //TODO
        return supplier.get().map(Employee::new)
                .filter(e -> e.getLastname().length() > 14)
                .map(Employee::getFirstname)
                .findAny().orElse("None");
    }

    /**
     * Calcule le nombre d'employé à mi-temps de la boîte
     * @return le nombre d'employé à mi-temps de la boîte
     */
    private static long numbreOfPartTimers() {
        //TODO
        return supplier.get().map(Employee::new).filter(e -> !e.isFullTime()).count();
    }

    /**
     * Catégorise les employés selon qu'ils travaillent à mi-temps ou à plein temps.
     * @return une map de liste des id des employés selon qu'ils travaillent à mi-temps (false)
     *         ou à plein temps (true)
     */
    private static Map<Boolean, List<Integer>> timeDistrubution() {
        //TODO
        return supplier.get().map(Employee::new).collect(
                Collectors.partitioningBy(
                        Employee::isFullTime,
                        Collectors.mapping(Employee::getId, Collectors.toList())
                )
        );

    }

    /**
     * Permet au Consumer d'utiliser le supplier défini en début de classe pour
     * effectuer ses opérations.
     * @param consumer un consommateur de Stream de String.
     */
    private static void withLines(Consumer<Stream<String>> consumer) {
        //TODO: try-with-resources avec le Supplier. Le consumer doit utiliser (en utilisant sa méthode accept())
        //      le résultat du Supplier.
        try (Stream<String> lines = supplier.get()){
            consumer.accept(lines);
        } catch (UncheckedIOException e){
            e.printStackTrace();
        }
    }

    /**
     * Fournit un Consumer à withLines. Le Consumer doit print le plus long nom de famille du fichier
     */
    private static void printLongestName() {
        withLines( lines -> System.out.println(
                lines.map( e -> new Employee(e).getLastname()).max(Comparator.comparing(String::length)).get()
        ));
    }



}
