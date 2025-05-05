package domaine;

import java.time.LocalDate;
import java.util.Objects;

public class Caisse {
    private String reference; //identifiant de la caisse
    private LocalDate dateLimite; // date limite à laquelle la caisse doit être transportée
    private String villeDepart;
    private String villeArrivee;
    private double poids;

    /**Le constructeur lance une IllegalArgumentException si la date limite n'est pas ultérieure
     * à la date actuelle.
     */
    public Caisse(String reference, LocalDate dateLimite, String villeDepart, String villeArrivee, double poids) {
        LocalDate dateActuelle = LocalDate.now();
        if (!dateLimite.isAfter(dateActuelle)) throw new IllegalArgumentException();
        this.reference = reference;
        this.dateLimite = dateLimite;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.poids = poids;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public double getPoids() {
        return poids;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caisse caisse = (Caisse) o;
        return Objects.equals(reference, caisse.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }


}
