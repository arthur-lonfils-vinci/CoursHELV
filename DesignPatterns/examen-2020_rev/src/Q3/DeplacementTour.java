package Q3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeplacementTour implements Deplacement {
	private List<Deplacement> enfants = new ArrayList<Deplacement>();

	public DeplacementTour(int taille, int source, int destination, int auxiliaire) {
		if (taille == 1) {
			enfants.add(new DeplacementDisque(source, destination));
			return;
		}
		enfants.add(new DeplacementTour(taille - 1, source, auxiliaire, destination));
		enfants.add(new DeplacementDisque(source, destination));
		enfants.add(new DeplacementTour(taille - 1, auxiliaire, destination, source));
	}

	public void stocker(List<DeplacementDisque> deplacements) {
		Iterator<Deplacement> it = enfants.iterator();
		while (it.hasNext()) {
			Deplacement d = (Deplacement) it.next();
			d.stocker(deplacements);
		}
	}
}
