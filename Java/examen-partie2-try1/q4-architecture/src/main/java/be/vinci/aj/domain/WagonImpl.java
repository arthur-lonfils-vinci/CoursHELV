package be.vinci.aj.domain;

public class WagonImpl extends VehiculeImpl implements Wagon {

    private int capacite;

    public WagonImpl(String id, String modele, int poids, int capacite) {
        super(id, modele, poids);
        this.capacite = capacite;
    }

    @Override
    public int getCapacite() {
        return capacite;
    }

}
