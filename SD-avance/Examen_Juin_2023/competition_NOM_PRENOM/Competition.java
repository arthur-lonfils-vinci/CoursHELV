import java.util.SortedSet;
import java.util.TreeSet;

public class Competition {
	private SortedSet<Athlete> athletes= new TreeSet<Athlete>();
	
	public Competition(Athlete... athletes) {
		for (Athlete a:athletes) {
			this.athletes.add(a);
		}
	}
	
	public void ajouterSaut(Athlete a, int longueur) {
		athletes.remove(a);
		a.ajouterSaut(longueur);
		athletes.add(a);
	}
	
	public void classement() {
		int i=1;
		for(Athlete a:athletes) {
			System.out.println(i+" "+a.getNom());
			i++;
		}
	}
	

}
