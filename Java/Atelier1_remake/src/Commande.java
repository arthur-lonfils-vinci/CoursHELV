import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande> {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesDeCommande;

    public Commande(Client client) {
        Util.checkObject(client);
        this.client = client;
        if (!client.enregistrer(this))
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        this.numero = numeroSuivant++;
        this.date = LocalDateTime.now();
        this.lignesDeCommande = new ArrayList<>();
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

    public boolean ajouter(Pizza pizza, int quantite) {
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        if (client.getCommandeEnCours() != this) return false;
        for (LigneDeCommande ligne : lignesDeCommande) {
            if (ligne.getPizza().equals(pizza)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return true;
            }
        }
        return lignesDeCommande.add(new LigneDeCommande(pizza, quantite));
    }

    public boolean ajouter(Pizza pizza) {
        return ajouter(pizza, 1);
    }

    public boolean retirer(Pizza pizza, int quantite) {
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        if (client.getCommandeEnCours() != this) return false;
        for (LigneDeCommande ligne : lignesDeCommande) {
            if (ligne.getPizza().equals(pizza)) {
                if (ligne.getQuantite() < quantite) return false;
                if (ligne.getQuantite() == quantite) return lignesDeCommande.remove(ligne);
                ligne.setQuantite(ligne.getQuantite() - quantite);
                return true;
            }
        }
        return false;
    }

    public boolean retirer(Pizza pizza) {
        return retirer(pizza, 1);
    }

    public boolean supprimer(Pizza pizza) {
        Util.checkObject(pizza);
        if (client.getCommandeEnCours() != this) return false;
        for (LigneDeCommande ligne : lignesDeCommande) {
            if (ligne.getPizza().equals(pizza)) {
                return lignesDeCommande.remove(ligne);
            }
        }
        return false;
    }

    public double calculerMontantTotal() {
        double montantTotal = 0;
        for (LigneDeCommande ligne : lignesDeCommande) {
            montantTotal += ligne.calculerPrixTotal();
        }
        return montantTotal;
    }

    public String detailler() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande n° ").append(numero).append(" du ").append(date).append(" pour ").append(client).append(" :\n");
        for (LigneDeCommande ligne : lignesDeCommande) {
            sb.append(ligne).append("\n");
        }
        sb.append("Montant total : ").append(calculerMontantTotal());
        return sb.toString();
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return lignesDeCommande.iterator();
    }

    @Override
    public String toString() {
       return detailler();
    }
}
