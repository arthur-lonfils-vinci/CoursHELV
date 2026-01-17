package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import validation.*;

import domaine.CarteDeCredit;

public class MainClient {
	public static void main(String[] args) throws IOException {
		File file = new File("cc.txt");
		// Chaine de Responsabilite
		Generateur generateur = new VisaVal();
		Generateur master = new MasterCardVal();
		Generateur amex = new AmExVal();
		Generateur diners = new DinersClubVal();
		Generateur discover = new DiscoverVal();

		// Chainage
		generateur.setSuccesseur(master);
		master.setSuccesseur(amex);
		amex.setSuccesseur(diners);
		diners.setSuccesseur(discover);

		try {
			Scanner fichier = new Scanner(file);
			while (fichier.hasNextLine()) {
				try {
					String ligne = fichier.nextLine();
					String[] parts = ligne.split(",");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
					Calendar dateExpiration = new GregorianCalendar();
					dateExpiration.setTime(sdf.parse(parts[1]));
					CarteDeCredit carteDeCredit = generateur.verify(parts[0], dateExpiration, parts[2]);
					if (carteDeCredit == null)
						System.out.println("Num�ro de carte invalide " + parts[0]);
					else {
						System.out.println("La carte " + carteDeCredit.getNumero() + " est une carte " + carteDeCredit.getType());
						System.out.println("Elle expire le " + sdf.format(dateExpiration.getTime()));
						System.out.println("Son titulaire est " + carteDeCredit.getNom());
					}
					System.out.println("-----------------------------------------------------------");
				} catch (ParseException e) {
					System.out.println("Mauvaise date d'expiration");
					e.printStackTrace();
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier " + file.getCanonicalPath() + " est introuvable");
			e.printStackTrace();
		}
	}
}
