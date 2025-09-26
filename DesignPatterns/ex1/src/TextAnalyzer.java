import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {
    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void readFile(String fileName) throws IOException {
        BufferedReader lecteurAvecBuffer = new BufferedReader(new FileReader(fileName));
        String ligne;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
            for (Observer observer : observers) {
                observer.readLines(ligne);
            }
        }
        for (Observer observer : observers) {
            observer.printResult();
        }
        lecteurAvecBuffer.close();
    }
}
