package domaine;

import exceptions.QuantiteNonAutoriseeException;
import util.Util;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Prix {

    private final TypePromo typePromo;
    private final double valeurPromo;
    private SortedMap<Integer,Double> prixParQuantite = new TreeMap<Integer,Double>();

    public Prix() {
        typePromo = null;
        valeurPromo = 0;
    }

    public Prix(TypePromo typePromo, double valeurPromo) {
        Util.checkObject(typePromo);
        Util.checkStrictlyPositive(valeurPromo);
        this.typePromo = typePromo;
        this.valeurPromo = valeurPromo;
    }

    public TypePromo getTypePromo() {
        return typePromo;
    }

    public double getValeurPromo() {
        return valeurPromo;
    }

    /**
     * Cette méthode permet de définir le prix unitaire correspondant à une quantité minimale.
     * S'il existe déjà un prix correspondant à la quantité, il sera remplacé.
     *
     * @param quantiteMin
     * @param prixUnitaire le prix unitaire à partir de cette quantité
     * @throws IllegalArgumentException en cas de quantité négative ou nulle ou en cas de valeur négative ou nulle
     */
    public void definirPrix(int quantiteMin, double prixUnitaire) {
        Util.checkStrictlyPositive(quantiteMin);
        Util.checkStrictlyPositive(prixUnitaire);
        prixParQuantite.put(quantiteMin,prixUnitaire);
    }

    /**
     * Cette méthode renvoie le prix à appliquer selon la quantité achetée
     *
     * @param quantite la quantité achetée
     * @return le prix unitaire pour cette quantité
     * @throws QuantiteNonAutoriseeException si la quantité est inférieure à la quantité minimale autorisée
     * @throws IllegalArgumentException      en cas de quantité négative ou nulle
     */
    public double getPrix(int quantite) throws QuantiteNonAutoriseeException {
        Util.checkStrictlyPositive(quantite);
        SortedMap<Integer,Double> quantiteInferieure = prixParQuantite.headMap(quantite+1);
        if (quantiteInferieure.isEmpty()) throw new QuantiteNonAutoriseeException();
        return quantiteInferieure.get(quantiteInferieure.lastKey());
    }

    @Override
    public String toString() {
        String detail = "";
        if (typePromo != null) detail += "Promo : " + typePromo + " - " + valeurPromo + "\n";
        for (Entry<Integer,Double> entry : prixParQuantite.entrySet()
             ) {
            detail += entry.getValue() + " euros à partir de " + entry.getKey() + " unités" + "\n";
        }
        return detail;
    }

}
