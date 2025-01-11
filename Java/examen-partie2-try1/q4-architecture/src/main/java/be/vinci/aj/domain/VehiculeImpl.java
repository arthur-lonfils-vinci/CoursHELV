package be.vinci.aj.domain;

public class VehiculeImpl implements Vehicule {

    private String id;
    private String modele;
    private int poids;

    public VehiculeImpl(String id, String modele, int poids) {
        if (poids <= 0) throw new IllegalArgumentException("Le poids doit être plus grand que zéro.");
        this.id = id;
        this.modele = modele;
        this.poids = poids;
    }

    public VehiculeImpl() {
        super();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getModele() {
        return modele;
    }

    @Override
    public int getPoids() {
        return poids;
    }

}
