package be.vinci.aj.domain;

import java.util.List;

public interface Train {
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
    boolean ajouterVehicule(Vehicule vehicule);

    void demarrer();

    List<Vehicule> getVehicules();

    int getTempsParcours();

    int getPuissance();

    public void setDepart(String depart);

    public void setArrivee(String arrivee);

    public void setTempsParcours(int tempsParcours);
}
