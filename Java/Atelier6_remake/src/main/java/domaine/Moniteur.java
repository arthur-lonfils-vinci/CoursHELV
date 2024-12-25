package domaine;

import java.util.List;

public interface Moniteur {
    /**
     * renvoie le nom du moniteur
     */
    String getNom();

    /**
     * recherche si le moniteur est libre durant la semaine passée en paramètre. Le
     * moniteur est libre s'il n'a pas de stage prévu cette semaine-là.
     *
     * @param numeroDeSemaine Le numéro de la semaine
     * @return true si le moniteur est libre durant la semaine passée en paramètre.
     * @throws IllegalArgumentException Exception lancée si le numéro de semaine
     *                                  n'est pas compris entre 1 et 8.
     */
    boolean estLibre(int numeroDeSemaine);

    /**
     * ajoute un stage au moniteur. Ce stage est ajouté si le moniteur n’avait pas de stage
     * cette semaine-là, qu’il fait bien partie des moniteurs pouvant encadrer des stages
     * dans ce sport et si le stage n’avait pas déjà un autre moniteur. La méthode
     * s'assure aussi que le stage retient aussi le moniteur associé.
     *
     * @param stage le stage à ajouter au moniteur.
     * @return true si le moniteur peut effectuer ce stage. Le stage est stocké dans
     * ses stages.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    boolean ajouterStage(Stage stage);

    /**
     * supprime le stage du moniteur. Ce stage doit se trouver dans ses stages afin
     * de pouvoir le supprimer.
     * <p>
     * La méthode s'assure aussi que le stage supprime aussi le moniteur associé.
     *
     * @param stage le stage à supprimer du moniteur.
     * @return true si le stage peut être enlevé du moniteur. false si le stage ne
     * fait pas partie des stages du moniteur.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    boolean supprimerStage(Stage stage);

    /**
     * teste si le stage est un stage effectué par le moniteur.
     *
     * @param stage le stage à tester.
     * @return true si le stage est bien un stage du moniteur. false si le stage ne
     * fait pas partie des stages du moniteur.
     * @throws IllegalArgumentException Exception lanc"e si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    boolean contientStage(Stage stage);

    /**
     * renvoie le nombre de stages effectués par le moniteur
     *
     * @return le nombre de stages.
     */
    int nombreDeStages();

    /**
     * renvoie les stages dans une liste
     *
     * @return la liste des stages du moniteur.
     */
    List<Stage> stages();
}
