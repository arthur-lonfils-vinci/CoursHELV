import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Coureur {
	private final int id;
	private final String nom;
	private LocalTime depart;
	private LocalTime arrivee;
	
	public Coureur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public LocalTime getDepart() {
		return depart;
	}
	public void setDepart(LocalTime depart) {
		this.depart = depart;
	}
	public LocalTime getArrivee() {
		return arrivee;
	}
	public void setArrivee(LocalTime arrivee) {
		if (depart==null||arrivee.isBefore(depart)) throw new IllegalArgumentException();
		this.arrivee = arrivee;
	}
	@Override
	public String toString() {
		return "Coureur [id=" + id + ", nom=" + nom + ", Temps: "+obtenirTemps()+"]";
	}
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	// renvoie le temps en secondes si le coureur est déjà arrivé
	// sinon lance une exception
	public long obtenirTemps() {
		if(arrivee==null) throw new IllegalStateException();
		return ChronoUnit.SECONDS.between(depart, arrivee);
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
		Coureur other = (Coureur) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
