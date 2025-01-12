package domaine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Trajet implements Iterable<Caisse>{
    private String numero; //identifiant du trajet
    private LocalDate date;
    private String villeDepart;
    private String villeArrivee;
    private Set<Caisse> caisses = new HashSet<>();

    /**Le constructeur lance une IllegalArgumentException si la date n'est pas ultérieure
     * à la date actuelle.
     */
    public Trajet(String numero, LocalDate date, String villeDepart, String villeArrivee) {
        LocalDate dateActuelle = LocalDate.now();
        if (!date.isAfter(dateActuelle)) throw new IllegalArgumentException();
        this.numero = numero;
        this.date = date;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    /**ajoute une caisse à transporter lors du trajet
     * @param caisse la caisse à ajouter
     * @return false si la caisse ne peut pas lui être ajoutée
     */
    public boolean ajouter(Caisse caisse){
        if (!peutAjouter(caisse)) return false;
        return caisses.add(caisse);
    }

    /**
     * renvoie true si la caisse peut être ajoutée au trajet
     * @param caisse
     * @return false si
     * - la date du jour n'est pas antérieure à la date du trajet
     * - la caisse se trouve déjà dans celles à tranporter
     * - la date du trajet est ultérieure à la date limite de transport de la caisse
     * - la ville de départ ou d'arrivée du trajet ne correspond pas à celle de la caisse
     * @throws IllegalArgumentException si le paramètre est null
     */
    public boolean peutAjouter(Caisse caisse) {
        if (caisse == null) {
            throw new IllegalArgumentException();
        }
        LocalDate dateActuelle = LocalDate.now();
        if (!dateActuelle.isBefore(date)) return false;
        if (caisses.contains(caisse)) return false;
        if (this.date.isAfter(caisse.getDateLimite())) return false;
        if (!villeDepart.equals(caisse.getVilleDepart())) return false;
        if (!villeArrivee.equals(caisse.getVilleArrivee())) return false;
        return true;
    }

    public boolean remove(Caisse caisse){
        return caisses.remove(caisse);
    }

    public double calculerPoidsTotal(){
        double poidsTotal = 0;

        for (Caisse caisse : caisses) {
            poidsTotal += caisse.getPoids();
        }
        return poidsTotal;
    }

    public int nbCaisses(){
        return caisses.size();
    }

    @Override
    public Iterator<Caisse> iterator() {
        return caisses.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trajet palettes = (Trajet) o;
        return Objects.equals(numero, palettes.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        String infos = "Trajet prévu le " + formater.format(date) + "\nVille de départ : " + villeDepart
                + "\nVille d'arrivée : " + villeArrivee + "\nNombre de palettes à transporter : " + caisses.size()
                + "\nPoids total : " + calculerPoidsTotal() + "kg";

        return infos;
    }
}
