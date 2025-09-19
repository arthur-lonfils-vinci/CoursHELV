import java.util.HashSet;
import java.util.List;

public class PalindromeCounter implements Observer {
    private int nbPalindromes = 0;
    private HashSet<String> palindromes;

    public PalindromeCounter() {
        palindromes = new HashSet<>();
    }

    public int getNbPalindromes() {
        return nbPalindromes;
    }

    public String getPalindromes() {
        return palindromes.toString();
    }

    @Override
    public void readLines(String line) {
        for (String word : line.split("[\\s.,;?!()&]+")) {
            if (word.contentEquals(new StringBuilder(word).reverse()) && !palindromes.contains(word)) {
                nbPalindromes++;
                palindromes.add(word);
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Nombre de palindromes : " + getNbPalindromes() + "\nPalindromes : " + getPalindromes());
    }
}
