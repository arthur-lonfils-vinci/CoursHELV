import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande>{

    public static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> ligneDeCommande;

    public Commande(Client client) {

        if (client.getCommandeEnCours() != null)
            throw new IllegalArgumentException("Impossible de creer un commande");

        this.client = client;
        this.date = LocalDateTime.now();
        this.ligneDeCommande = new ArrayList<>();
        this.numero = numeroSuivant++;

        client.enregistrer(this);
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite){
        if (client.getCommandeEnCours() == null){return false;}

        for (LigneDeCommande ligne : ligneDeCommande) {
            if (ligne.getPizza().equals(pizza)){
                ligne.setQuantite(ligne.getQuantite()+quantite);
                return true;
            }
        }

        ligneDeCommande.add(new LigneDeCommande(pizza, quantite));
        return true;
    }

    public boolean ajouter(Pizza pizza){
        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal(){
        double prix = 0;
        for (LigneDeCommande ligne : ligneDeCommande) {
            prix += ligne.calculerPrixTotal();
        }
        return prix;
    }

    public String detailler(){
        String info = "\n";
        for (LigneDeCommande ligne : ligneDeCommande) {
            info += ligne.toString() + "\n";
        }
        return info;
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }


    @Override
    public Iterator<LigneDeCommande> iterator() {
        return null;
    }
}
