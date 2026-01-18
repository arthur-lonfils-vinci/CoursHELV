package Q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Hanoi {
	private static HashMap<Integer, Hanoi> instances = new HashMap<Integer, Hanoi>();
	private final int taille;
	private ArrayList<DeplacementDisque> deplacements = new ArrayList<DeplacementDisque>();

	private Hanoi(int taille) {
		this.taille = taille;
	}

	public static Hanoi getInstance(int taille) {
		Hanoi instance = (Hanoi) instances.get(new Integer(taille));
		if (instance == null)
			instances.put(new Integer(taille), instance = new Hanoi(taille));
		return instance;
	}

	public int getTaille() {
		return taille;
	}

	public Iterator<DeplacementDisque> iterator() {
		return deplacements.iterator();
	}

	public void resoudre() {
		new DeplacementTour(taille, 0, 2, 1).stocker(deplacements);
	}
}
