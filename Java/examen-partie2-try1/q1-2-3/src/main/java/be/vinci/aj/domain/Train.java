package be.vinci.aj.domain;

import java.util.ArrayList;
import java.util.List;

public class Train implements Runnable{

    private final List<Vehicule> vehicules = new ArrayList<Vehicule>();

    private String depart;
    private String arrivee;
    private int tempsParcours; // En milisecondes

    private int puissance = 0; // Somme de la puissance des locomotives du train

    public Train(String depart, String arrivee, int tempsParcours) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.tempsParcours = tempsParcours;
    }

    /**
     * Permet d'ajouter un véhicule (locomotive/wagon) au Train.
     * Si le véhicule est une locomotive, il sera donc toujours ajouté en début de liste.
     * Sinon, le véhicule est ajouté en fin de liste.
     * La puissance totale des locomotives du train est également mise à jour lors de l'ajout d'une locomotive
     * <p>
     * Dans un train, la puissance doit être plus grande ou égale à la somme du poids de tous les véhicules,
     * locomotives comprises.
     * On ne peut donc pas ajouter un véhicule qui ferait en sorte que le train soit trop lourd par rapport
     * à sa puissance.
     * Par exemple, dans un train avec une puissance de 6000, et un poids de 5000, on ne peut pas ajouter un
     * véhicule de poids 2000, sinon ça ferait un poids de 7000, ce qui est plus que la puissance du train (6000).
     * <p>
     * On ne peut pas ajouter une locomotive dont la puissance est inférieure au poids.
     *
     * @param vehicule le véhicule à ajouter.
     * @throws IllegalArgumentException Si le véhicule est une locomotive dont le poids est inférieur à la puissance.
     */
    public boolean ajouterVehicule(Vehicule vehicule) {
        if (vehicule instanceof Locomotive) {
            int puissance = ((Locomotive) vehicule).getPuissance();
            if (puissance < vehicule.getPoids()) throw new IllegalArgumentException();
            this.vehicules.add(0, vehicule);
            this.puissance += puissance;
            return true;
        }
        if (this.puissance < this.vehicules.stream().mapToInt(Vehicule::getPoids).sum()+vehicule.getPoids()) {
            return false;
        }
        this.vehicules.add(vehicule);
        return true;
    }


    public void demarrer() {
        System.out.println("Le train [" + this.depart + "-" + this.arrivee + "] démarre.");
        try {
            Thread.sleep(this.tempsParcours);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Le train [" + this.depart + "-" + this.arrivee + "] est arrivé après " + this.tempsParcours + "ms.");
    }


    public List<Vehicule> getVehicules() {
        return vehicules;
    }


    public int getTempsParcours() {
        return tempsParcours;
    }


    public int getPuissance() {
        return puissance;
    }

    @Override
    public void run() {
        this.demarrer();
    }
}
