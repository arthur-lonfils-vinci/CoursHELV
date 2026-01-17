public class Vit extends Activite {
	public Vit(Cellule cellule) {
		super(cellule);
	}

	public void activer() {
		getCellule().vit();
	}
}
