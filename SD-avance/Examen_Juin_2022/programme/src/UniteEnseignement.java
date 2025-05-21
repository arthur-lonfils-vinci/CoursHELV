// La classe peut être modifiée si nécéssaire

public class UniteEnseignement {
	private final String nom;
	private final int nbEcts;

	public UniteEnseignement(String nom, int nbEcts) {
		super();
		this.nom = nom;
		this.nbEcts = nbEcts;
	}
	
	public String getNom() {
		return nom;
	}
	public int getNbEcts() {
		return nbEcts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		UniteEnseignement other = (UniteEnseignement) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
}
