import java.util.ArrayList;
import java.util.Objects;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees = new ArrayList<Commande>();

    public Client(String nom, String prenom, String telephone) {
        Util.checkString(nom);
        Util.checkString(prenom);
        Util.checkString(telephone);
        this.numero = numeroSuivant++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getTelephone() {
        return this.telephone;
    }

    //Commande en cours
    public Commande getCommandeEnCours() {
        return this.commandeEnCours;
    }

    public boolean enregistrer(Commande commande) {
        Util.checkObject(commande);
        if (this.commandeEnCours != null) return false;
        if (!commande.getClient().equals(this)) return false;
        if (commandesPassees.contains(commande)) return false;
        this.commandeEnCours = commande;
        return true;
    }

   public boolean cloturerCommandeEnCours() {
        if (this.commandeEnCours == null) return false;
        this.commandesPassees.add(this.commandeEnCours);
        this.commandeEnCours = null;
        return true;
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
