import decorator.Counter;
import strategy.PalindromeStrategy;
import strategy.StartsWithStrategy;
import strategy.Strategy;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WordLister lister = new WordLister("test_belgique.txt");

        System.out.println("-----------------------A-----------------------");

        Strategy s1 = new StartsWithStrategy("t");
        Counter c1 = new Counter(s1);
        lister.printSelectedWords(c1);

        System.out.println("-----------------------");

        Strategy s2 = new PalindromeStrategy();
        Counter c2 = new Counter(s2);
        lister.printSelectedWords(c2);

    }
}
