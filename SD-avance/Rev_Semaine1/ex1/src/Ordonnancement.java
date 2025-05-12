import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;
	public static final int NIVEAU_PRIORITE_MIN=1;
	public Map<Integer, List<Tache>> priorites;

	public Ordonnancement(){
		priorites = new TreeMap<>();
		for (int i = 1; i <= NIVEAU_PRIORITE_MAX; i++) {
			priorites.put(i, new ArrayList<>());
		}
	}
	public void ajouterTache (String descriptif, int priorite) {
		if (priorite<NIVEAU_PRIORITE_MIN || priorite>NIVEAU_PRIORITE_MAX) {
			throw new IllegalArgumentException("priorite invalide");
		}

		priorites.get(priorite).addLast(new Tache(descriptif, priorite));
	}
	
	//renvoie la tache prioritaire
	//renvoie null si plus de tache presente
	public Tache attribuerTache(){
		for (int i = NIVEAU_PRIORITE_MAX; i >= NIVEAU_PRIORITE_MIN; i--) {
			if (!priorites.get(i).isEmpty()) {
				return priorites.get(i).removeFirst();
			}
		}
		return null;
	}
}
