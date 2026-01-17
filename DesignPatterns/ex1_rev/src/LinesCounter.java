public class LinesCounter implements Observer {
    int count = 0;

    @Override
    public void readLine(String line) {
        count++;
    }

    @Override
    public void printResult() {
        System.out.println("Number of lines: " + count);
    }
}
