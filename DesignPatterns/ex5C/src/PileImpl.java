public class PileImpl implements Pile {
	private NoeudPile tete;
	private int taille;

	public PileImpl() {
		this.tete = null;
	}

	public void push(Object element) {
		this.tete = new NoeudPile(element, this.tete);
		this.taille++;
	}

	public Object pop() {
		Object resultat = sommet();
		this.tete = this.tete.getSuivant();
		this.taille--;
		return resultat;
	}

	public Object sommet() {
		return this.tete.getElement();
	}

	public boolean estVide() {
		return this.tete == null;
	}

	public int taille() {
		return this.taille;
	}

	public String toString() {
		NoeudPile courant = this.tete;
		String resultat = "";
		while (courant != null) {
			resultat += courant.getElement();
			resultat += " ";
			courant = courant.getSuivant();
		}
		return resultat;
	}

	class NoeudPile {
		private Object element;
		private NoeudPile suivant;

		public NoeudPile(Object element) {
			this(element, null);
		}

		public NoeudPile(Object element, NoeudPile suivant) {
			setElement(element);
			setSuivant(suivant);
		}

		public void setElement(Object elem) {
			this.element = elem;
		}

		public void setSuivant(NoeudPile suiv) {
			this.suivant = suiv;
		}

		public Object getElement() {
			return this.element;
		}

		public NoeudPile getSuivant() {
			return this.suivant;
		}
	}
}
