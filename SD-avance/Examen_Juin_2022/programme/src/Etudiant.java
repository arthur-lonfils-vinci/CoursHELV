// La classe peut être modifiée si nécéssaire

public class Etudiant {
	private final int numeroRegistreNational;
	private final String nom;
	private final String prenom;
	private int nbEctsValides;

	public Etudiant(int numeroRegistreNational, String nom, String prenom) {
		super();
		this.numeroRegistreNational = numeroRegistreNational;
		this.nom = nom;
		this.prenom = prenom;
		this.nbEctsValides=0;
	}

	public int getNumeroRegistreNational() {
		return numeroRegistreNational;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNbEctsValides() {
		return nbEctsValides;
	}

	public void setNbEctsValides(int nbEctsValides) {
		this.nbEctsValides = nbEctsValides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroRegistreNational;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (numeroRegistreNational != other.numeroRegistreNational)
			return false;
		return true;
	}
	
	

}
