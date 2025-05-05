package main;

import domaine.Caisse;
import domaine.Camion;
import domaine.Trajet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Camion camion = new Camion("Q-ABC-123", 10,16400);
        LocalDate dateFuture = LocalDate.now().plusDays(10);
        Trajet trajetMaTo = new Trajet("MA-TO-025",dateFuture.plusDays(24),"Marseille","Toulouse");
        for (int i = 1; i<=5;i++){
            trajetMaTo.ajouter(new Caisse("CA-14"+i,dateFuture.plusDays(i+30),"Marseille","Toulouse",800));
        }
        camion.ajouterTrajet(trajetMaTo);
        Trajet trajetMaLy1 = new Trajet("MA-LY-025",dateFuture,"Marseille","Lyon");
        for (int i = 1; i<=7;i++){
            trajetMaLy1.ajouter(new Caisse("CA-0"+i,dateFuture.plusDays(i),"Marseille","Lyon",800));
        }
        camion.ajouterTrajet(trajetMaLy1);
        Trajet trajetMaLy2 = new Trajet("MA-LY-035",dateFuture.minusDays(5),"Marseille","Lyon");
        for (int i = 1; i<=10;i++){
            trajetMaLy1.ajouter(new Caisse("CA-1"+i,dateFuture.plusDays(i+5),"Marseille","Lyon",800));
        }
        camion.ajouterTrajet(trajetMaLy2);
        Trajet trajetLyLi1 = new Trajet("Ly-Li-025",dateFuture.plusDays(1),"Lyon","Lille");
        for (int i = 1; i<=8;i++){
            trajetMaLy1.ajouter(new Caisse("CA-0"+i,dateFuture.plusDays(i),"Lyon","Lille",800));
        }
        camion.ajouterTrajet(trajetLyLi1);
        Trajet trajetMaLy3 = new Trajet("MA-LY-045",dateFuture.plusDays(3),"Marseille","Lyon");
        for (int i = 1; i<=7;i++){
            trajetMaLy1.ajouter(new Caisse("CA-4"+i,dateFuture.plusDays(i+5),"Marseille","Lyon",800));
        }
        camion.ajouterTrajet(trajetMaLy3);
        Trajet trajetMaLy4 = new Trajet("MA-LY-055",dateFuture.minusDays(3),"Marseille","Lyon");
        for (int i = 1; i<=6;i++){
            trajetMaLy1.ajouter(new Caisse("CA-3"+i,dateFuture.plusDays(i+5),"Marseille","Lyon",800));
        }
        camion.ajouterTrajet(trajetMaLy4);
        System.out.println("Test de trouverTrajetsAvecPlaceRestante ");
        List<Trajet> trajetsAvecPlaceRestante = camion.trouverTrajetsAvecPlaceRestante();
        System.out.println("Nombre de trajets où il reste de la place pour les caisses (5 attendu) : " + trajetsAvecPlaceRestante.size());
        System.out.println("Affichage des villes de départ et d'arrivée des trajets pour vérifier l'ordre : ");
        System.out.println("Affichage attendu : ");
        System.out.println("Lyon-->Lille\nMarseille-->Lyon\nMarseille-->Lyon\nMarseille-->Lyon\nMarseille-->Toulouse");
        System.out.println("Affichage trouvé : ");
        for (Trajet trajet : trajetsAvecPlaceRestante) {
            System.out.println(trajet.getVilleDepart() + "-->" + trajet.getVilleArrivee());
        }

        System.out.println();
        System.out.println("Test de trouverDateTrajet :");
        System.out.println("Date du premier trajet de Marseille à Lyon (" + dateFuture.minusDays(5) + " attendu) :" +
                camion.trouverDateTrajet("Marseille","Lyon"));
        System.out.println("Date du premier trajet de Marseille à Lille (null attendu) : " +
                camion.trouverDateTrajet("Marseille","Lille"));

        System.out.println();
        System.out.println("Test de nombreDeCaissesParDateLimite : ");
        Trajet trajetNonAttribue = new Trajet("PA-BO-025",dateFuture,"Paris","Bordeaux");
        for (int i = 1; i<=5;i++){
            trajetNonAttribue.ajouter(new Caisse("CA-0"+i,dateFuture.plusDays(2*i),"Paris","Bordeaux",800));
        }
        for (int i = 1; i<=3;i++){
            trajetNonAttribue.ajouter(new Caisse("CA-1"+i,dateFuture.plusDays(3*i),"Paris","Bordeaux",800));
        }
        for (int i = 1; i<=2;i++){
            trajetNonAttribue.ajouter(new Caisse("CA-2"+i,dateFuture.plusDays(4*i),"Paris","Bordeaux",800));
        }
        for (int i = 1; i<=2;i++){
            trajetNonAttribue.ajouter(new Caisse("CA-3"+i,dateFuture.plusDays(2*i+2),"Paris","Bordeaux",800));
        }
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        Map<LocalDate,Long> nbCaissesSelonDateLimite = trajetNonAttribue.nombreDeCaissesParDateLimite();
        System.out.println("Affichage attendu (l'ordre peut être différent) : ");
        System.out.println(formater.format(dateFuture.plusDays(2)) + " --> 1 caisse(s)\n" +
                formater.format(dateFuture.plusDays(3)) + " --> 1 caisse(s)\n" +
                formater.format(dateFuture.plusDays(4)) + " --> 3 caisse(s)\n" +
                formater.format(dateFuture.plusDays(6)) + " --> 3 caisse(s)\n" +
                formater.format(dateFuture.plusDays(8)) + " --> 2 caisse(s)\n" +
                formater.format(dateFuture.plusDays(9)) + " --> 1 caisse(s)\n" +
                formater.format(dateFuture.plusDays(10)) + " --> 1 caisse(s)");
        System.out.println("Affichage trouvé : ");
        for(Map.Entry<LocalDate,Long> entry : nbCaissesSelonDateLimite.entrySet()){
            System.out.println(formater.format(entry.getKey()) + " --> " + entry.getValue() + " caisse(s)");
        }
    }
}
