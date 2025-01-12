package main;

import domaine.Caisse;
import domaine.Camion;
import domaine.Trajet;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Camion camion = new Camion("Q-ABC-123", Camion.Gabarit.DEUX_ESSIEUX);
        LocalDate dateTrajet = LocalDate.now().plusDays(10);
        Trajet trajetTropLourd = new Trajet("BX-PA-001",dateTrajet,"Bruxelles","Paris");
        trajetTropLourd.ajouter(new Caisse("CA-0001",dateTrajet.plusDays(1),"Bruxelles","Paris",16500));
        System.out.println("Appel d'ajouter trajet avec trop de poids (false attendu) : " +camion.ajouterTrajet(trajetTropLourd));

        Trajet trajetTropDeCaisses = new Trajet("LY-PA-015",dateTrajet,"Lyon","Paris");
        for (int i = 1; i<=21;i++){
            trajetTropDeCaisses.ajouter(new Caisse("CA-0"+i,dateTrajet.plusDays(i),"Lyon","Paris",100));
        }
        System.out.println("Appel d'ajouter trajet avec trop de caisses (false attendu) : " +camion.ajouterTrajet(trajetTropDeCaisses));

        Trajet trajetValide = new Trajet("MA-LY-025",dateTrajet,"Marseille","Lyon");

        for (int i = 1; i<=19;i++){
            trajetValide.ajouter(new Caisse("CA-0"+i,dateTrajet.plusDays(i),"Marseille","Lyon",800));
        }
        System.out.println("Appel d'ajouter trajet avec un trajet valide (true attendu) : " +camion.ajouterTrajet(trajetValide));
        Caisse caisseTropLourde = new Caisse("CA-205",dateTrajet.plusDays(10),"Marseille","Lyon",1800);
        System.out.println("Ajout d'une caisse trop lourde pour le trajet du camion (false attendu) : " + camion.ajouterCaisse(caisseTropLourde));

        Caisse caisseValide = new  Caisse("CA-205",dateTrajet.plusDays(10),"Marseille","Lyon",800);
        System.out.println("Ajout d'une caisse valide au camion (true attendu) : " + camion.ajouterCaisse(caisseValide));

        Caisse caisseEnTrop = new  Caisse("CA-226",dateTrajet.plusDays(10),"Marseille","Lyon",100);
        System.out.println("Ajout d'une caisse en trop pour le trajet du camion (false attendu) : " + camion.ajouterCaisse(caisseEnTrop));

    }
}
