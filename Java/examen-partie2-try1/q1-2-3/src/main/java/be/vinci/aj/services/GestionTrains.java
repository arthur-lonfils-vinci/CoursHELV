package be.vinci.aj.services;

import be.vinci.aj.domain.Train;

public class GestionTrains extends Thread {

    public void demarrerTrains() {
        Train train1 = new Train("Bruxelles", "Namur", 1500);
        Train train2 = new Train("Bruxelles", "Ostende", 2000);
        Train train3 = new Train("Bruxelles", "Louvain", 1000);

        Thread t = new Thread(train1);
        Thread t2 = new Thread(train2);
        Thread t3 = new Thread(train3);

        t.start();
        t2.start();
        t3.start();
    }

}
