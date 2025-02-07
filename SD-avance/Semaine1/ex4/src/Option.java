import java.util.HashSet;
import java.util.Set;


public class Option {
	private String name;
	private int nbMaxEtudiant; 
	private Set<Etudiant> etudiantsInscrits;
	public Option(String name, int nbMaxEtudiant) {
		super();
		this.name = name;
		this.nbMaxEtudiant = nbMaxEtudiant;
		etudiantsInscrits= new HashSet<Etudiant>();
	}
	
	// s'il n'y a plus de place, retourne false
	// s'il y a encore de la place, inscrit l'�tudiant et retourne true
	public boolean inscrireEtudiant(Etudiant etu){
		if (etudiantsInscrits.size()==nbMaxEtudiant) return false;
		etudiantsInscrits.add(etu);
		return true;
	}

	public String getName() {
		return name;
	}

	public int getNbMaxEtudiant() {
		return nbMaxEtudiant;
	}

	public String toString(){
		String s=this.name+"\n";
		if (etudiantsInscrits.isEmpty())s+="Pas d'inscrit";
		for (Etudiant e:etudiantsInscrits){
			s=s+e.getNom()+" ";
		}
		return s;
	}
}
