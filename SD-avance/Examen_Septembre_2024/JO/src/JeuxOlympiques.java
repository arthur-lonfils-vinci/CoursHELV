import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;

public class JeuxOlympiques {
	private HashMap<String, ArrayList<Pays>> medailles;
	private SortedSet<Pays> classement;
	
	public JeuxOlympiques() {
		medailles= new HashMap<String,ArrayList<Pays>>();
		classement= new TreeSet<Pays>();
	}
	
	public void ajouterResultat(String discipline, Pays or, Pays argent,Pays bronze) {
		ArrayList<Pays> ordre=new ArrayList<Pays>();
		ordre.add(or);
		ordre.add(argent);
		ordre.add(bronze);
		medailles.put(discipline, ordre);
		classement.remove(or);
		classement.remove(argent);
		classement.remove(bronze);
		or.ajouterMedailleOr();
		argent.ajouterMedailleArgent();
		bronze.ajouterMedailleBronze();
		classement.add(or);
		classement.add(argent);
		classement.add(bronze);
	}
	
	public void afficherClassement() {
		System.out.println("nom"+"\t\t"+"or"+"\t"+"argent"+"\t"+"bronze");
		for (Pays p: classement) {
			System.out.println(p);
		}
	}
}
