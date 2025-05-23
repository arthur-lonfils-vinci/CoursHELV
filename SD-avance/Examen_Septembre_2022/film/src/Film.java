// La classe peut être modifiée si nécéssaire

public class Film {
	private static int nbfilms=1; 
	private final int id;
	private final String nom;
	private final int annee;
	public Film(String nom, int annee) {
		this.id=nbfilms;
		nbfilms++;
		this.nom=nom;
		this.annee=annee;
	}
	public String getNom() {
		return nom;
	}
	public int getAnnee() {
		return annee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Film [nom=" + nom + ", annee=" + annee + "]";
	}
}
