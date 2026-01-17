import decorator.Counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class WordLister {
    private final String filePath;

    public WordLister(String filePath) {
        this.filePath = filePath;
    }

    public void printSelectedWords(Counter counter) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, " \t.;(){}\"'*=:!/\\");
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                if (counter.isSelected(word)) {
                    System.out.println(word);
                }
            }
        }

        reader.close();

    }
}
