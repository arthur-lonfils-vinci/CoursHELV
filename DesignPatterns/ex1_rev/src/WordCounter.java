import java.util.Arrays;

public class WordCounter implements Observer {

    private int count = 0;

    @Override
    public void readLine(String line) {
        count += line.split(" ").length;
    }

    @Override
    public void printResult() {
        System.out.println("Number of words: " + count);
    }
}
