import java.util.*;

public class Academie {
	HashMap<String, List<String>> elevesEnAttente; // instrument -> eleves
	HashMap<String, List<String>> elevesInscrits; // instrument -> eleves

	// list instrument
	public Academie(ArrayList<String> v){
		elevesEnAttente = new HashMap<String, List<String>>();
		for (String instrument : v){
			elevesEnAttente.put(instrument, new LinkedList<String>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		if (elevesEnAttente.containsKey(instrument)){
			elevesEnAttente.get(instrument).addLast(nomEleve);
		}
	} 
	
	// supprime uniquement l'�l�ve de la file d'attente et le renvoie
	// renvoie null s�il n�y a pas d��l�ve en attente pour cet instrument	
	public String attribuerPlace(String instrument){
		if (elevesEnAttente.containsKey(instrument)){
			if (!elevesEnAttente.get(instrument).isEmpty()){
				String eleve = elevesEnAttente.get(instrument).getFirst();
				elevesEnAttente.get(instrument).removeFirst();
				return eleve;
			}
		}
		return null;
	} 

}
