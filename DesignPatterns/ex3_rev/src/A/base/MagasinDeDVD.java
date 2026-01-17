package A.base;

import java.util.HashMap;
import java.util.Map;


public class MagasinDeDVD {
	private Map<String,DVD> bac= new HashMap<String,DVD>();
	public void ajouterDVD(String name, int anneeDeParution){
		DVD dvd=new DVD(name, anneeDeParution);
		bac.put(name,dvd);
	}
	public DVD retourneDVD(String name){
		return bac.get(name);
	}
}
