//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domaine;

import java.util.Set;

public class StageStub implements Stage {
    private final int getNumeroDeSemaine;
    private final Sport getSport;
    private final Moniteur getMoniteur;

    public StageStub(int getNumeroDeSemaine, Sport getSport, Moniteur getMoniteur) {
        this.getNumeroDeSemaine = getNumeroDeSemaine;
        this.getSport = getSport;
        this.getMoniteur = getMoniteur;
    }

    public String getIntitule() {
        return null;
    }

    public String getLieu() {
        return null;
    }

    public int getNumeroDeSemaine() {
        return this.getNumeroDeSemaine;
    }

    public Sport getSport() {
        return this.getSport;
    }

    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }

    public boolean supprimerMoniteur() {
        return false;
    }

    public Moniteur getMoniteur() {
        return this.getMoniteur;
    }

    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    public int nombreDEnfants() {
        return 0;
    }

    public Set<Enfant> enfants() {
        return null;
    }
}
