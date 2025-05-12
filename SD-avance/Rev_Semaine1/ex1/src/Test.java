public class Test {
	public static void main(String[] args) {
		Ordonnancement o = new Ordonnancement();
		o.ajouterTache("faire mes exercices de SD", 5);
		o.ajouterTache("faire la vaisselle", 3);
		o.ajouterTache("regarder le foot", 5);
		o.ajouterTache("faire mes exercices de C", 1);
		for (int i = 0; i < 4; i++) {
			Tache t = o.attribuerTache();
			System.out.println("La tache " + t.getDescriptif() + " de niveau " + t.getPriorite() + " a �t� affect�e");
		}
	}
}
