import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	HashMap<Integer, Queue<Integer>> pointuresCasiers;
	HashMap<Integer, LocalTime> casiersAttribues;
	HashSet<Integer> casiersLibres;
	
	public LocationPatins(int[] casiers) {
		pointuresCasiers = new HashMap<>();
		casiersLibres = new HashSet<>();
		for (int i = 0; i < casiers.length; i++) {
			if (!pointuresCasiers.containsKey(casiers[i])) {
				pointuresCasiers.put(casiers[i], new LinkedList<>());
			}
			pointuresCasiers.get(casiers[i]).add(i);
			casiersLibres.add(i);
		}

		casiersAttribues = new HashMap<>();
	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();

		//a compl�ter
		if (!casiersLibres.isEmpty()){
			for (Integer casier: casiersLibres) {
				if (pointuresCasiers.get(pointure).contains(casier)) {
					casiersLibres.remove(casier);
					casiersAttribues.put(casier, LocalTime.now());
					return casier;
				}
			}
		}

		return -1;
	}

	public double libererCasier(int numeroCasier) {
		//a completer
		if (casiersAttribues.containsKey(numeroCasier)) {
			LocalTime l = LocalTime.now();
			double prix = prix(casiersAttribues.get(numeroCasier), l);
			casiersAttribues.remove(numeroCasier);
			casiersLibres.add(numeroCasier);
			return prix;
		}
		return 0;
	}

}
