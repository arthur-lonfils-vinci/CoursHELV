package domaine;

import java.util.Set;

public class StageStub implements Stage {

    private final int numSemaine;
    private final Sport sport;
    private final Moniteur moniteur;

    public StageStub(int numSemaine, Sport sport, Moniteur moniteur) {
        this.numSemaine = numSemaine;
        this.sport = sport;
        this.moniteur = moniteur;
    }

    @Override
    public String getIntitule() {
        return "";
    }

    @Override
    public String getLieu() {
        return "";
    }

    @Override
    public int getNumeroDeSemaine() {
        return this.numSemaine;
    }

    @Override
    public Sport getSport() {
        return this.sport;
    }

    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }

    @Override
    public boolean supprimerMoniteur() {
        return false;
    }

    @Override
    public Moniteur getMoniteur() {
        return this.moniteur;
    }

    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public int nombreDEnfants() {
        return 0;
    }

    @Override
    public Set<Enfant> enfants() {
        return Set.of();
    }
}
