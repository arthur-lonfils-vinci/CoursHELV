package validation;

import domaine.CarteDeCredit;
import domaine.DinersClub;

import java.util.Calendar;

public class DinersClubVal extends Generateur {
    @Override
    public boolean valider(String numero) {
        return numero.matches("36\\d{12}");
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        return new DinersClub(numero, dateExpiration, nom);
    }
}
