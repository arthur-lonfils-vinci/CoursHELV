// Cette classe représente un joueur automatique.
// Elle maintient à jour un arbre des meileurs coups.
public class AutomaticPlayer extends SimpleSpectator implements Player {

	// Noeud courant de jeu. C'est le noeud qui correspond à l'état courant du jeu.
	private Tree currentNode;

	// En plus du contrat de Spectator, cette méthode :
	// 1) Initialise l'arbre du jeu, et
	// 2) calcule les valeurs Minimax pour chaque noeud de l'arbre.
	@Override
	public void start(State state) {
		super.start(state);
		currentNode = new Tree(state);
		currentNode.computeMinimaxValues();
	}

	// En plus du contrat de Spectator, cette méthode maintient currentNode,
	// cad le noeud qui correspond à l'état courant du jeu.
	@Override
	public void play(boolean isLeftMove, State state) {
		super.play(isLeftMove, state);
		if (isLeftMove) {
			currentNode = currentNode.getLeftChild();
		} else {
			currentNode = currentNode.getRightChild();
		}
	}

	// Cette méthode est appelée pour connaitre le coup de ce joueur :
	// 1) Elle renvoie vrai si ce joueur joue la barre de gauche, et
	// 2) Elle renvoie faux si ce joueur joue la barre de droite.
	@Override
	public boolean nextPlay() {
		return currentNode.getMinimaxValue().isLeftMove();
	}
}
