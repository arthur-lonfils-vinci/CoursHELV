package validation;

import java.util.Calendar;

import domaine.CarteDeCredit;

public abstract class Generateur {
	private Generateur successeur;

	public void setSuccesseur(Generateur successeur) {
		this.successeur = successeur;
	}

	public CarteDeCredit verify(String numero, Calendar dateExpiration, String nom) {
		if (valider(numero)) {
			return creerCarte(numero, dateExpiration, nom);
		}

		if (successeur != null) {
			return successeur.verify(numero, dateExpiration, nom);
		}

		return null;
	}

	public abstract boolean valider(String numero);
	public abstract CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom);
}
