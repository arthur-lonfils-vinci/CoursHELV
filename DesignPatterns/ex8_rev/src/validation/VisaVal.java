package validation;

import domaine.CarteDeCredit;
import domaine.Visa;

import java.util.Calendar;

public class VisaVal extends Generateur {
    @Override
    public boolean valider(String numero) {
        return numero.matches("4\\d{15}");
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        return new Visa(numero, dateExpiration, nom);
    }
}
