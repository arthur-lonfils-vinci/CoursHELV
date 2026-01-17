import java.util.List;

public class ParcourtFort extends Parcourir {
	public void parcourtCelluleVivante(Cellule cellule, JeuDeLaVie jeu, List<Activite> activites) {
		int n = cellule.nombreDeVoisins(jeu);
		if (n != 2 && n != 3) {
			activites.add(new Meurt(cellule));
		}
	}

	public void parcourtCelluleMorte(Cellule cellule, JeuDeLaVie jeu, List<Activite> activites) {
		int n = cellule.nombreDeVoisins(jeu);
		if (n == 3 || n == 2) {
			activites.add(new Vit(cellule));
		}
	}
}
