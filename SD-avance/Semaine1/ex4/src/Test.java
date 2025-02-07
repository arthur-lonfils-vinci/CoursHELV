public class Test {
	public static void main(String[] args) {
		ChoixOptions co= new ChoixOptions(2, "IA","SAP","Unity");
		Etudiant jean= new Etudiant("jean", 80);
		Etudiant paul= new Etudiant("paul", 81);
		Etudiant luc= new Etudiant("luc",85);
		co.ajouterPreferences(jean, "IA", "SAP", "Unity");
		co.ajouterPreferences(paul, "IA", "SAP", "Unity");
		co.ajouterPreferences(luc, "IA", "SAP", "Unity");
		co.attribuerOptions();
		System.out.println(co);
	}
}
