import java.time.LocalTime;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
  public HashMap<Integer, LocalTime> patinsDuree = new HashMap<>();
	public int[] casiers;

  public LocationPatins(int[] casiers) {
    this.patinsDuree = new HashMap<>();
		this.casiers = casiers;
  }

  // date1 < date2
  private static double prix(LocalTime date1, LocalTime date2) {
    // 1 euro par milliseconde (c'est assez cher en effet)
    return MILLIS.between(date1, date2);
  }

  public int attribuerCasierAvecPatins(int pointure) {
    if (pointure < 33 || pointure > 48)
      throw new IllegalArgumentException();
    LocalTime l = LocalTime.now();


    //a compl�ter

		for (int i=0; i<casiers.length; i++) {
			if (casiers[i] == pointure && patinsDuree.get(i) == null) {
				patinsDuree.put(i, l);
				return i;
			}
		}
		return -1;
  }

  public double libererCasier(int numeroCasier) {
    //a completer
		if (numeroCasier < 0 || numeroCasier >= casiers.length)
			throw new IllegalArgumentException();

		LocalTime l1 = patinsDuree.get(numeroCasier);
		LocalTime l2 = LocalTime.now();
		patinsDuree.remove(numeroCasier);

		return prix(l1, l2);
  }

}
