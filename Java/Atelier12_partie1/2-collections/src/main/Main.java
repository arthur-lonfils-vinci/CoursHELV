package main;

import domaine.Caisse;
import domaine.Camion;
import domaine.Trajet;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tests d'ajouterTrajet : ");
        Camion camion = new Camion("Q-ABC-123", 20,16400);
        LocalDate dateTrajet = LocalDate.now().plusDays(10);
        Trajet trajetValide = new Trajet("MA-LY-025",dateTrajet,"Marseille","Lyon");

        for (int i = 1; i<=15;i++){
            trajetValide.ajouter(new Caisse("CA-0"+i,dateTrajet.plusDays(i),"Marseille","Lyon",800));
        }
        System.out.println("Appel d'ajouter trajet avec un trajet valide (true attendu) : " +camion.ajouterTrajet(trajetValide) );

        Trajet trajetDatePrise = new Trajet("MA-LY-030",dateTrajet,"Strasbourg","Lille");
        System.out.println("Appel d'ajouter trajet avec un trajet à une date occupée (false attendu) : " +camion.ajouterTrajet(trajetDatePrise));

        Trajet trajetVilleArriveeKO = new Trajet("MA-LY-031",dateTrajet.minusDays(1),"Strasbourg","Lille");
        System.out.println("Appel d'ajouter trajet avec un trajet la veille d'un autre mais pas la bonne ville d'arrivée (false attendu) : " +camion.ajouterTrajet(trajetVilleArriveeKO));

        Trajet trajetVilleDépartKO = new Trajet("MA-LY-031",dateTrajet.plusDays(1),"Strasbourg","Lille");
        System.out.println("Appel d'ajouter trajet avec un trajet le lendemain d'un autre mais pas la bonne ville de départ (false attendu) : " +camion.ajouterTrajet(trajetVilleDépartKO));

        Trajet trajetVilleArriveeOK = new Trajet("MA-LY-032",dateTrajet.minusDays(1),"Strasbourg","Marseille");
        System.out.println("Appel d'ajouter trajet avec un trajet la veille d'un autre avec pas la bonne ville d'arrivée (true attendu) : " +camion.ajouterTrajet(trajetVilleArriveeOK));

        Trajet trajetVilleDépartOK = new Trajet("MA-LY-033",dateTrajet.plusDays(1),"Lyon","Lille");
        System.out.println("Appel d'ajouter trajet avec un trajet le lendemain d'un autre mais avec la bonne ville de départ (true attendu) : " +camion.ajouterTrajet(trajetVilleDépartOK));

        System.out.println("\nTest de trouverTrajet : ");
        System.out.println("Récupération du à partir d'une date présente (trajet du " + dateTrajet + " de Marseille à Lyon attendu) : ");
        System.out.println(camion.trouverTrajet(dateTrajet)+"\n");

        System.out.println("Tentative de récupérer un trajet à une date inexistante (null attendu) : " + camion.trouverTrajet(dateTrajet.plusMonths(1)));

        System.out.println("\nTest d'ajouterCaisse (permet de tester rechercherTrajet)");
        Caisse caisseDateLimiteKO = new  Caisse("CA-212",dateTrajet,"Marseille","Lyon",800);
        System.out.println("Ajout d'une caisse dont la date limite de transport est celle du trajet du camion (true attendu) : " + camion.ajouterCaisse(caisseDateLimiteKO));
        Caisse caisseDateLimiteKO2 = new  Caisse("CA-213",dateTrajet.minusDays(1),"Marseille","Lyon",800);
        System.out.println("Ajout d'une caisse dont la date limite de transport est avant celle du trajet du camion (false attendu) : " + camion.ajouterCaisse(caisseDateLimiteKO2));
        Caisse caisseValide = new  Caisse("CA-214",dateTrajet.plusDays(1),"Marseille","Lyon",800);
        System.out.println("Ajout d'une caisse dont la date limite de transport est après celle du trajet du camion (true attendu) : " + camion.ajouterCaisse(caisseValide));
    }
}
