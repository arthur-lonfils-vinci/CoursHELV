import java.util.Objects;

public class Client {
    static private int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;

    public Client(String prenom, String nom, String telephone) {
        numero = numeroSuivant++;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;

    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public boolean enregistrer (Commande commande) {
        if (commandeEnCours != null || !commande.getClient().equals(this)) {
            return false;
        }else {
            commandeEnCours = commande;
            return true;
        }
    }

    public boolean cloturerCommandeEnCours () {
        if (commandeEnCours == null ) {
            return false;
        }else {
            commandeEnCours = null;
            return true;
        }
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
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }

}
