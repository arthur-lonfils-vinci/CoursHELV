import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class TextAnalyzer {
	private HashSet<Observer> observers;

	public TextAnalyzer() {
		observers = new HashSet<>();
	}

	public void readFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			for (Observer observer : observers) {
				observer.readLine(line);
			}
		}

		for (Observer observer : observers) {
			observer.printResult();
		}

		br.close();
	}

	public void  registerObserver(Observer observer) {
		observers.add(observer);
	}
}
