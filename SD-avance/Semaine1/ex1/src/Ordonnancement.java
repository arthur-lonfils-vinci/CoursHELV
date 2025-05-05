import java.util.*;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;
	private HashMap<Integer, Queue<Tache>> taches;

	public Ordonnancement(){
		taches = new HashMap<>();
		for (int i = 1; i <= NIVEAU_PRIORITE_MAX; i++) {
			taches.put(i, new LinkedList<>());
		}
	}
	public void ajouterTache (String descriptif, int priorite){
		taches.get(priorite).add(new Tache(descriptif,priorite));
	}

	//renvoie la tache prioritaire
	//renvoie null si plus de tache presente
	public Tache attribuerTache(){
		for (int i = NIVEAU_PRIORITE_MAX; i >= 1; i--) {
			if (!taches.get(i).isEmpty()) {
				return taches.get(i).poll();
			}
		}
		return null;

	}
}