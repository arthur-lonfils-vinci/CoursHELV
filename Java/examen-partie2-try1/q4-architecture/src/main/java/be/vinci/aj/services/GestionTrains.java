package be.vinci.aj.services;

import be.vinci.aj.domain.*;

public class GestionTrains {

    TrainFactory trainFactory;

    public GestionTrains(TrainFactory trainFactory) {
        this.trainFactory = trainFactory;
    }

    public void demarrerTrains() {
        String depart = "Bruxelles";

        Train train1 = trainFactory.getTrain();
        train1.setDepart(depart);
        train1.setArrivee("Brugges");
        train1.setTempsParcours(1500);

        Train train2 = trainFactory.getTrain();
        train2.setDepart(depart);
        train2.setArrivee("Ostende");
        train2.setTempsParcours(2000);

        Train train3 = trainFactory.getTrain();
        train3.setDepart(depart);
        train3.setArrivee("Louvain");
        train3.setTempsParcours(1000);

        train1.demarrer();
        train2.demarrer();
        train3.demarrer();
    }

}
