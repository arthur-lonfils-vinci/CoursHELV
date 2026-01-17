package validation;

import domaine.CarteDeCredit;
import domaine.Discover;

import java.util.Calendar;

public class DiscoverVal extends Generateur {
    @Override
    public boolean valider(String numero) {
        return numero.matches("(6011\\d{12}|65\\d{14})");
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        return new Discover(numero, dateExpiration, nom);
    }
}
