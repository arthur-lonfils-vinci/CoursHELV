package be.vinci.aj.domain;

public class Wagon extends Vehicule {

    private int capacite;

    public Wagon(String id, String modele, int poids, int capacite) {
        super(id, modele, poids);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

}
