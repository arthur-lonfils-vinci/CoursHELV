package be.vinci.aj.domain;

public class Vehicule {

    private String id;
    private String modele;
    private int poids;

    public Vehicule(String id, String modele, int poids) {
        if (poids <= 0) throw new IllegalArgumentException("Le poids doit être plus grand que zéro.");
        this.id = id;
        this.modele = modele;
        this.poids = poids;
    }


    public String getId() {
        return id;
    }


    public String getModele() {
        return modele;
    }


    public int getPoids() {
        return poids;
    }

}
