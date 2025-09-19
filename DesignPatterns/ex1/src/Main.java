import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TextAnalyzer analyzer = new TextAnalyzer();

        Observer palindromeCounter = new PalindromeCounter();
        Observer wordOccurenceCounter = new WordOccurenceCounter("Belgique");
        Observer lineCounter = new LineCounter();
        Observer wordCounter = new WordCounter();

        analyzer.registerObserver(palindromeCounter);
        analyzer.registerObserver(wordOccurenceCounter);
        analyzer.registerObserver(lineCounter);
        analyzer.registerObserver(wordCounter);

        analyzer.readFile("test_belgique.txt");
    }
}
