import java.util.*;

public abstract class Parcourir {
	public abstract void parcourtCelluleVivante(Cellule cellule, JeuDeLaVie jeu, List<Activite> activites);

	public abstract void parcourtCelluleMorte(Cellule cellule, JeuDeLaVie jeu, List<Activite> activites);
}
