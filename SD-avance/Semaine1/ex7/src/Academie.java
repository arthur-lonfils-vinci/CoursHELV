import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Academie {

	private HashMap<String, ArrayDeque<String>> fileAttente;
	public Academie(ArrayList<String> v){
		fileAttente = new HashMap<String, ArrayDeque<String>>();
		for (String instrument : v) {
			fileAttente.put(instrument, new ArrayDeque<String>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		fileAttente.get(instrument).addLast(nomEleve);
	} 
	
	// supprime uniquement l'�l�ve de la file d'attente et le renvoie
	// renvoie null s�il n�y a pas d��l�ve en attente pour cet instrument	
	public String attribuerPlace(String instrument){
		if (!fileAttente.get(instrument).isEmpty()) {
			return fileAttente.get(instrument).removeFirst();
		}
		return null;
	} 

}
