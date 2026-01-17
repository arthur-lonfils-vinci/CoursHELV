public class WordOccurenceCounter implements Observer{

    private int count = 0;
    private String occurrence;

    public WordOccurenceCounter(String occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public void readLine(String line) {
        for (String word: line.split("[\\s.,;?!()&]+")) {
            if (word.contentEquals(occurrence)) {
                count++;
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Number of occurrences: " + count);
    }
}
