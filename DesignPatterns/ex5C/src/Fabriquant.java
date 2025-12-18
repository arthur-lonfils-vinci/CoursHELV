public class Fabriquant {
	private Pile pile = new PileImpl();
	private Partie resultat;
	private int niveau = 0;
	char c = 'a';

	public void construireGroupe() {
		if (!pile.estVide()) {
			Groupe sommet = (Groupe) pile.pop();
			Groupe nouveau = new Groupe(String.valueOf(c++), ++niveau);
			sommet.add(nouveau);
			pile.push(sommet);
			pile.push(nouveau);
		} else {
			Groupe nouveau = new Groupe(String.valueOf(c++), 0);
			pile.push(nouveau);
			if (resultat == null) {
				resultat = nouveau;
			} else {
				throw new RuntimeException(); // fichier malformé
			}
		}
	}

	public void fermerGroupe() {
		pile.pop();
		niveau--;
	}

	public void construireValeur(int nombre) {
		Valeur number = new Valeur(nombre, niveau + 1);
		if (!pile.estVide()) {
			Groupe sommet = (Groupe) pile.pop();
			sommet.add(number);
			pile.push(sommet);
		} else
			resultat = number;
	}

	public Partie getResultat() {
		return resultat;
	}
}
