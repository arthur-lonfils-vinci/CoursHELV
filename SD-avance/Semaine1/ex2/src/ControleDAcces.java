import java.util.HashMap;

public class ControleDAcces {
	private HashMap<Badge, Employe> badges;
	private HashMap<Employe, Boolean> presence;
	
	public ControleDAcces(){
		badges = new HashMap<>();
		presence = new HashMap<>();
	}
	
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e){
		badges.put(b, e);
		presence.put(e, false);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b){
		Employe e = badges.get(b);
		presence.put(e, true);
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b){
		Employe e = badges.get(b);
		presence.put(e, false);
	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e){
		return presence.get(e);
	}

}
