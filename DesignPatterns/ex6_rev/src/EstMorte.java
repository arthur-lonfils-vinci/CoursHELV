import java.util.List;

public class EstMorte extends Situation {
  private static EstMorte instance = null;

  private EstMorte() {}

  public static EstMorte getInstance() {
    if (instance == null)
      instance = new EstMorte();
    return instance;
  }

  public Situation vit() {
    return EstVivante.getInstance();
  }

  public Situation meurt() {
    return this;
  }

  public boolean estVivante() {
    return false;
  }

  public Situation toggle() {
    return vit();
  }

  public void ajouterAuxVoisinsVivants(List<Situation> voisinsVivants) { // ne pas s'ajouter : on
                                                                         // est mort
  }

  public void generer(Cellule cellule, JeuDeLaVie jeu, List<Activite> activites,
      Parcourir parcourt) {
    parcourt.parcourtCelluleMorte(cellule, jeu, activites);
  }
}

