package validation;

import domaine.CarteDeCredit;
import domaine.MasterCard;

import java.util.Calendar;

public class MasterCardVal extends Generateur{
    @Override
    public boolean valider(String numero) {
        return numero.matches("5[1-5]\\d{14}");
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        return new MasterCard(numero, dateExpiration, nom);
    }
}
