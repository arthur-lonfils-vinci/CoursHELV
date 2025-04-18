// Cette classe repr�sente un joueur automatique.
// Elle maintient � jour un arbre des meileurs coups.
public class AutomaticPlayer extends SimpleSpectator implements Player {

  // Noeud courant de jeu. C'est le noeud qui correspond � l'�tat courant du jeu.
  private Tree currentNode;

  // En plus du contrat de Spectator, cette m�thode :
  // 1) Initialise l'arbre du jeu, et
  // 2) calcule les valeurs Minimax pour chaque noeud de l'arbre.
  @Override
  public void start(State state) {
    // TODO
    super.start(state);
    currentNode = new Tree(state);
    currentNode.computeMinimaxValues();
  }

  // En plus du contrat de Spectator, cette m�thode maintient currentNode,
  // cad le noeud qui correspond � l'�tat courant du jeu.
  @Override
  public void play(boolean isLeftMove, State state) {
    // TODO
    super.play(isLeftMove, state);
    if (isLeftMove) {
      currentNode = currentNode.getLeftChild();
    } else {
      currentNode = currentNode.getRightChild();
    }
  }

  // Cette m�thode est appel�e pour connaitre le coup de ce joueur :
  // 1) Elle renvoie vrai si ce joueur joue la barre de gauche, et
  // 2) Elle renvoie faux si ce joueur joue la barre de droite.
  @Override
  public boolean nextPlay() {
    // TODO
    return currentNode.getMinimaxValue().isLeftMove();
  }
}
