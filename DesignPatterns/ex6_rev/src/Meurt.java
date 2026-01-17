public class Meurt extends Activite {
	public Meurt(Cellule cellule) {
		super(cellule);
	}

	public void activer() {
		getCellule().meurt();
	}
}
