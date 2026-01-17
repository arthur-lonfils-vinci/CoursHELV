public abstract class Activite {
	private Cellule cellule;

	public Activite(Cellule cellule) {
		this.cellule = cellule;
	}

	// Envoyer la requête sauvée (vit ou meurt) à la cellule. 
	public abstract void activer();
	public Cellule getCellule() {
		return cellule;
	}
}
