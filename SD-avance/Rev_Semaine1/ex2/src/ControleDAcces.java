import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControleDAcces {
	public HashMap<Employe, Badge> employeBadge;
	public List<Badge> presentBatiment;
	
	public ControleDAcces(){
		employeBadge = new HashMap<>();
		presentBatiment = new ArrayList<>();
	}
	
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e){
		employeBadge.put(e, b);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b) {
		presentBatiment.add(b);
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b){
		presentBatiment.remove(b);
	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e){
		return presentBatiment.contains(employeBadge.get(e));
	}

}
