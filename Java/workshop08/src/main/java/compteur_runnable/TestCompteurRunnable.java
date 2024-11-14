package compteur_runnable;

import java.util.ArrayList;
import java.util.List;

public class TestCompteurRunnable {

	public static void main(String[] args) {
		List<CompteurRunnable> compteurs = new ArrayList<>();
		long start = System.currentTimeMillis();

		compteurs.add(new CompteurRunnable("Bolt", 10));
		compteurs.add(new CompteurRunnable("Jakson", 10));
		compteurs.add(new CompteurRunnable("Robert", 10));
		compteurs.add(new CompteurRunnable("Stéphanie", 10));

		for (CompteurRunnable compteurRunnable : compteurs) {
			// TODO: lancer les compteurs
		}

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Durée avant d'atteindre cette instruction de fin du programme principal : " + duration + " ms");

	}

}
