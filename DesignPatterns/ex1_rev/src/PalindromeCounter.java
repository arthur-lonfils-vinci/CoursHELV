import java.util.HashSet;

public class PalindromeCounter implements Observer {

    private HashSet<String> palindromes;

    public PalindromeCounter() {
        palindromes = new HashSet<>();
    }

    @Override
    public void readLine(String line) {
        for (String word : line.split("[\\s.,;?!()&']+")) {
            if (word.contentEquals(new StringBuffer(word).reverse())) {
                palindromes.add(word);
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Number of palindromes: " + palindromes.size());
        palindromes.forEach(System.out::println);
    }
}
