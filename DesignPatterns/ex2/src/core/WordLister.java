package core;

import decorator.Compteur;

import java.io.*;
import java.util.StringTokenizer;

public class WordLister {
    private final String filePath;

    public WordLister(String filePath) {
        this.filePath = filePath;
    }

    public void printSelectedWords(Compteur compteur) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = input.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, " \t.;(){}\"'*=:!/\\");
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                if (compteur.isSelected(word)) {
                    System.out.println(word);
                }
            }
        }
        input.close();
    }
}
