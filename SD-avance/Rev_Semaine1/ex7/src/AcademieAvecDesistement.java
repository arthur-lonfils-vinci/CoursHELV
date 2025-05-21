import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class AcademieAvecDesistement {

	HashMap<String, Vector<String>> elevesEnAttente;
	HashMap<String, Vector<String>> elevesInscrits;

	public AcademieAvecDesistement(ArrayList<String> v){
		elevesEnAttente = new HashMap<String, Vector<String>>();
		elevesInscrits = new HashMap<String, Vector<String>>();

		for (String instrument : v){
			elevesEnAttente.put(instrument, new Vector<String>());
			elevesInscrits.put(instrument, new Vector<String>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		if (elevesEnAttente.containsKey(instrument)){
			elevesEnAttente.get(instrument).add(nomEleve);
		}
	} 
	
	public void desistement(String instrument, String eleve){
		if (elevesEnAttente.containsKey(instrument)){
      elevesEnAttente.get(instrument).remove(eleve);
		}
	}
	
	//supprime uniquement l'�l�ve de la file d'attente
	public String attribuerPlace(String instrument){
		if (elevesEnAttente.containsKey(instrument)){
			if (!elevesEnAttente.get(instrument).isEmpty()){
				String eleve = elevesEnAttente.get(instrument).getFirst();
				elevesEnAttente.get(instrument).removeFirst();
				elevesInscrits.get(instrument).add(eleve);
				return eleve;
			}
		}
		return null;
	} 
	
	

}
