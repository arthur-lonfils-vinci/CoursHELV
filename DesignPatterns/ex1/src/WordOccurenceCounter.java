public class WordOccurenceCounter implements Observer {
    private int nbOccurences = 0;
    private String word;

    public WordOccurenceCounter(String word) {
        this.word = word;
    }

    public int getNbOccurences() {
        return nbOccurences;
    }

    @Override
    public void readLines(String line) {
        for (String word : line.split(" ")) {
            if (word.equals(this.word)) {
                nbOccurences++;
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Nombre d'occurences de " + word + " : " + nbOccurences);
    }
}
