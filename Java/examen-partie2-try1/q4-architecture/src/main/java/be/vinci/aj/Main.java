package be.vinci.aj;

import be.vinci.aj.domain.TrainFactory;
import be.vinci.aj.domain.TrainFactoryImpl;
import be.vinci.aj.services.GestionTrains;

public class Main {

    public static void main(String[] args) {
        TrainFactory trainFactory = new TrainFactoryImpl();
        GestionTrains gestionTrains = new GestionTrains(trainFactory);
        gestionTrains.demarrerTrains();
    }

}