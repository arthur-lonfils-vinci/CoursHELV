package A.base;

import java.util.HashMap;
import java.util.Map;

public class MagasinDeLivre {
	private Map<String,Livre> etagere= new HashMap<String,Livre>();
	public void ajouterLivre(String name, int anneeDeParution){
		Livre livre=new Livre(name, anneeDeParution);
		etagere.put(name,livre);
	}
	public Livre retourneLivre(String name){
		return etagere.get(name);
	}
}
