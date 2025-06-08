public class Troncon {
	private final Ligne ligne;
	private final Station depart;
	private final Station arrivee;
	private final int duree;

	public Troncon(Ligne ligne, Station depart, Station arrivee, int duree) {
		this.ligne = ligne;
		this.depart = depart;
		this.arrivee = arrivee;
		this.duree = duree;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public Station getDepart() {
		return depart;
	}

	public Station getArrivee() {
		return arrivee;
	}

	public int getDuree() {
		return duree;
	}

	@Override
	public String toString() {
		return "Troncon [ligne=" + ligne.getNom() + ", depart=" + depart.getNom() + ", arrivee=" + arrivee.getNom() + ", duree=" + duree + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivee == null) ? 0 : arrivee.hashCode());
		result = prime * result + ((depart == null) ? 0 : depart.hashCode());
		result = prime * result + ((ligne == null) ? 0 : ligne.hashCode());
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
		Troncon other = (Troncon) obj;
		if (arrivee == null) {
			if (other.arrivee != null)
				return false;
		} else if (!arrivee.equals(other.arrivee))
			return false;
		if (depart == null) {
			if (other.depart != null)
				return false;
		} else if (!depart.equals(other.depart))
			return false;
		if (ligne == null) {
			if (other.ligne != null)
				return false;
		} else if (!ligne.equals(other.ligne))
			return false;
		return true;
	}

}
