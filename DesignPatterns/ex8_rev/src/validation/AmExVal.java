package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class AmExVal extends Generateur {
    @Override
    public boolean valider(String numero) {
        return numero.matches("3[47]\\d{13}");
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        return new domaine.AmEx(numero, dateExpiration, nom);
    }
}
