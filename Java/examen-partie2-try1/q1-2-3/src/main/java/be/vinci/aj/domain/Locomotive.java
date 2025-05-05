package be.vinci.aj.domain;

public class Locomotive extends Vehicule {

    public Locomotive(String id, String modele, int poids) {
        super(id, modele, poids);
    }


    public int getPuissance() {
        switch (this.getModele().toUpperCase()) {
            case "T12": return 3130;
            case "T13": return 5200;
            case "T18": return 5700;
            case "T19": return 6000;
            default: return 0;
        }
    }

}
