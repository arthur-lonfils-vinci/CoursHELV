import java.util.*;
import java.util.stream.Stream;


public class ProgrammesEtudiants {
	
	// ajouter/modifier attributs ici
	private Set<Etudiant> etudiants = new HashSet<Etudiant>();
	private HashMap<Etudiant, Set<UniteEnseignement>> etudiantSetHashMap;
	
	public ProgrammesEtudiants(Etudiant... etudiants) {
		etudiantSetHashMap = new HashMap<>();

		for (Etudiant etudiant : etudiants) {
			this.etudiants.add(etudiant);
			etudiantSetHashMap.put(etudiant, new HashSet<>());
		}
	}

	// A COMPLETER	
	// Enregistre la validation de l'unit� d'enseignement par l'�tudiant et met �
	// jour le nombre d'ects valid� par l'�tudiant.
	// Si l'unit� d'enseignement a d�j� �t� valid�e par l'�tudiant, la m�thode 
	// lance une RuntimeException avec le message 'ue d�j� valid�e'
	public void valider(Etudiant e, UniteEnseignement ue) throws RuntimeException {
		if (etudiantSetHashMap.get(e).contains(ue)){
			throw new RuntimeException();
		}

		etudiantSetHashMap.get(e).add(ue);
		e.setNbEctsValides(e.getNbEctsValides() + ue.getNbEcts());
	}
	
	// A COMPLETER
	// affiche la liste de tous les �tudiants (pr�nom, nom et nombre d'ects valid�s)
	// tri�s par le nombre d'ects valid�s
	// Voici un exemple de sortie attendue:
	// Alain Delcourt 10 ects
	// Pol Durant 8 ects
	// Jean Michel 0 ects
	// Si deux �tudiants ont le meme nombre d'ects valid�s, on affiche 
	// les deux �tudiants dans n'importe quel sens.
	public void afficherEtudiantsTriesParEcts() {
		etudiants.stream()
						.sorted(Comparator.comparingInt(Etudiant::getNbEctsValides).reversed())
						.forEach(e -> System.out.println(e.getPrenom() + " " + e.getNom() + " " + e.getNbEctsValides() + " ects"));
	}
	
	public static void main(String[] args) throws InterruptedException {
		Etudiant e1= new Etudiant(123456, "Durant", "Pol");
		Etudiant e2= new Etudiant(123453, "Delcourt", "Alain");
		Etudiant e3= new Etudiant(123452, "Michel", "Jean");
		ProgrammesEtudiants p= new ProgrammesEtudiants(e1,e2,e3);
		UniteEnseignement sd2= new UniteEnseignement("SD2", 4);
		UniteEnseignement bd2= new UniteEnseignement("BD2", 6);
		UniteEnseignement mobile= new UniteEnseignement("Mobile", 4);
		p.valider(e1, sd2);
		p.valider(e1, mobile);
		p.valider(e2, bd2);
		try {
			p.valider(e2, mobile);
		} catch (RuntimeException e) {
			System.out.println("eu déjà validée");
		}
		p.afficherEtudiantsTriesParEcts();
		Thread.sleep(50); //cette ligne est uniquement presente pour l'affichage
		p.valider(e1, mobile);
	}
}
