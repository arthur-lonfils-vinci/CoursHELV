import composite.And;
import composite.Et;
import core.WordLister;
import decorator.Compteur;
import strategy.LengthStrategy;
import strategy.PalindromeStrategy;
import strategy.SelectionStrategy;
import strategy.StartsWithStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        WordLister lister = new WordLister(filename);

        System.out.println("-------------------Ex2.1---------------------");

        SelectionStrategy strategy1 = new StartsWithStrategy("t");
        Compteur compteur1 = new Compteur(strategy1);
        lister.printSelectedWords(compteur1);
        System.out.println("Selected words: " + compteur1.getCount());

        System.out.println("----------------------------------------");

        SelectionStrategy strategy2 = new LengthStrategy(3);
        Compteur compteur2 = new Compteur(strategy2);
        lister.printSelectedWords(compteur2);
        System.out.println("Selected words: " + compteur2.getCount());

        System.out.println("----------------------------------------");

        SelectionStrategy strategy3 = new PalindromeStrategy();
        Compteur compteur3 = new Compteur(strategy3);
        lister.printSelectedWords(compteur3);
        System.out.println("Selected words: " + compteur3.getCount());


        System.out.println("----------------------------------------");
        System.out.println("-------------------Ex2.2---------------------");
        System.out.println("----------------------------------------");

        SelectionStrategy andStrategy = new And(
                new PalindromeStrategy(),
                new StartsWithStrategy("d")
        );
        Compteur compteur4 = new Compteur(andStrategy);
        lister.printSelectedWords(compteur4);
        System.out.println("Selected words: " + compteur4.getCount());

        System.out.println("----------------------------------------");
        System.out.println("-------------------Ex2.3---------------------");
        System.out.println("----------------------------------------");

        List<SelectionStrategy> strategies = new ArrayList<>();
        strategies.add(new StartsWithStrategy("k"));
        strategies.add(new PalindromeStrategy());
        strategies.add(new LengthStrategy(5));
        strategies.add(new And(new PalindromeStrategy(), new StartsWithStrategy("k")));

        SelectionStrategy etStrategy = new Et(strategies);
        Compteur compteur5 = new Compteur(etStrategy);
        lister.printSelectedWords(compteur5);
        System.out.println("Selected words: " + compteur5.getCount());
    }
}
