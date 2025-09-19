public class WordCounter implements Observer {
    private int nbWords = 0;

    public int getNbWords() {
        return nbWords;
    }
    @Override
    public void readLines(String line) {
        nbWords += line.split(" ").length;
    }

    @Override
    public void printResult() {
        System.out.println("Nombre de mots de : " + nbWords);
    }
}
