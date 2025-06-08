// Cette classe représente l'arbre du jeu.
public class Tree {

	// L'état du jeu correspondant à un noeud de l'arbre.
	private State state;

	// La valeur Minimax dans cet état.
	private Triplet minimaxValue;

	// null si le noeud courant est une feuille, le fils de gauche sinon.
	private Tree leftChild;

	// null si le noeud courant est une feuille, le fils de droite sinon.
	private Tree rightChild;

	// Ce constructeur construit l'arbre du jeu à partir de l'état state.
	// Notez que les valeurs Minimax seront calculée dans la méthode
	// computeMinimaxValues
	// et non dans ce constructeur.
	public Tree(State state) {
		this.state = state;
		if (state.getLeftBar() != state.getRightBar()) {
			this.leftChild = new Tree(state.playLeft());
			this.rightChild = new Tree(state.playRight());
		}
	}

	// Renvoie la valeur Minimax du joueur bleu en fonction des valeurs Minimax de
	// ses fils.
	private static Triplet minBlue(Triplet leftRes, Triplet rightRes) {
		if (leftRes.getMinBlue() > rightRes.getMinBlue()) {
			return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
		} else {
			return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
		}
	}

	// Renvoie la valeur Minimax du joueur orange en fonction des valeurs Minimax de
	// ses fils.
	private static Triplet minOrange(Triplet leftRes, Triplet rightRes) {
		if (leftRes.getMinOrange() > rightRes.getMinOrange()) {
			return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
		} else {
			return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
		}
	}

	// Calcule les valeurs Minimax de tout l'arbre.
	// En pratique, cette méthode calcule pour chaque noeud de l'arbre un nouveau
	// Triplet représentant les valeurs Minimax de chaque noeud.
	public void computeMinimaxValues() {
		//TODO
	}

	// Renvoie true si le noeud est une feuille, false sinon.
	public boolean isLeaf() {
		return leftChild == null;
	}

	// Getter de la valeur Minimax
	public Triplet getMinimaxValue() {
		return minimaxValue;
	}

	// Getter de l'état courant
	public State getState() {
		return state;
	}

	// Getter du fils de gauche
	public Tree getLeftChild() {
		return leftChild;
	}

	// Getter du fils de droite
	public Tree getRightChild() {
		return rightChild;
	}

	public int nbrNode() {
		int res = 1;
		if (!isLeaf()) {
			res += leftChild.nbrNode() + rightChild.nbrNode();
		}
		return res;
	}
}
