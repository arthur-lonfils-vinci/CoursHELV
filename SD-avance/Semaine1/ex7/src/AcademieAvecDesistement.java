import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class AcademieAvecDesistement {

	HashMap<String, Vector<String>> fileAttente;
	public AcademieAvecDesistement(ArrayList<String> v){
		fileAttente = new HashMap<String, Vector<String>>();
		for(String s : v){
			fileAttente.put(s, new Vector<String>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		fileAttente.get(instrument).add(nomEleve);
	} 
	
	public void desistement(String instrument, String eleve){
		fileAttente.get(instrument).remove(eleve);
	}
	
	//supprime uniquement l'�l�ve de la file d'attente
	public String attribuerPlace(String instrument){
		if(fileAttente.get(instrument).isEmpty()){
			return "Il n'y a plus de place pour cet instrument";
		}
		return fileAttente.get(instrument).removeFirst();
	} 
	
	

}
