import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Doodle {
	private PlageHoraire[] plages;
	// a compl�ter
	private HashMap<PlageHoraire, Set<String>> disponibilites;


	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		// a compl�ter
		disponibilites = new HashMap<PlageHoraire, Set<String>>();
		for (PlageHoraire plage : plages) {
			disponibilites.put(plage, new HashSet<String>());
		}
	}

	// ajoute les disponibilit�s d'un participant sous forme d'un tableau de booleen.
	// les indices du tableau correspondent aux indices du tableau plages
	// true � l'indice i veut dire que le participant est disponible pour la plage � l'indice i du tableau plages
	// false � l'indice i veut dire que le participant n'est pas disponible pour la plage � l'indice i du tableau plages
	public void ajouterDisponibilites(String participant,
			boolean[] disponibilites) {
		if (disponibilites.length != plages.length)
			throw new IllegalArgumentException();
		//a compl�ter
		for (int i = 0; i < disponibilites.length; i++) {
			if (disponibilites[i]) {
				this.disponibilites.get(plages[i]).add(participant);
				// set the nbParticipantPresent of plage
				plages[i].setNbParticipantPresent(plages[i].getNbParticipantPresent() + 1);
			}
		}
	}
	
	// Hypoth�se: la PlageHoraire plage en param�tre fait bien partie du tableau plages
	// renvoie vrai si le participant est disponible pour cette plage horaire
	// renvoie faux si le participant n'est pas disponible ou s'il n'a pas rempli le
	// sondage doodle
	public boolean estDisponible(String participant, PlageHoraire plage) {
    return disponibilites.get(plage).contains(participant);
  }

	// renvoie une des plages horaires qui a le maximum de participants pr�vus
	// cette m�thode est appel�e apr�s que les participants aient rempli leurs disponibilit�s
	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		// a compl�ter
		int max = 0;
		PlageHoraire plageMax = null;
		for (PlageHoraire plage : plages) {
			if (plage.getNbParticipantPresent() > max) {
				max = plage.getNbParticipantPresent();
				plageMax = plage;
			}
		}
		return plageMax;
	}

}
