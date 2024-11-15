package compteur_runnable;

import sync.Compteur;

// TODO : Veuillez implémenter l'interface Runnable (et sa méthode run)
//       Attention à garder l'héritage de Compteur !

public class CompteurRunnable extends Compteur implements Runnable {

    public CompteurRunnable(String nom, int max) {
        super(nom, max);
    }

    public void run() {
        count();
    }

}