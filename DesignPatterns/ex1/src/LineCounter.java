public class LineCounter implements Observer {
    private int nbLines = 0;

    public int getNbLines() {
        return nbLines;
    }

    @Override
    public void readLines(String line) {
        nbLines++;
    }

    @Override
    public void printResult() {
        System.out.println("Nombre de lignes : " + nbLines);
    }
}
