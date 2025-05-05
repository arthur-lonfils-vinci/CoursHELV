// Cette classe repr�sente l'arbre du jeu.
public class Tree {

  // L'�tat du jeu correspondant � un noeud de l'arbre.
  private State state;

  // La valeur Minimax dans cet �tat.
  private Triplet minimaxValue;

  // null si le noeud courant est une feuille, le fils de gauche sinon.
  private Tree leftChild;

  // null si le noeud courant est une feuille, le fils de droite sinon.
  private Tree rightChild;

  // Ce constructeur construit l'arbre du jeu � partir de l'�tat state.
  // Notez que les valeurs Minimax seront calcul�e dans la m�thode computeMinimaxValues
  // et non dans ce constructeur.
  public Tree(State state) {
    this.state = state;

    // Check if we've reached the end of the game (no more numbers to take)
    if (state.getLeftBar() < state.getRightBar()) {
      // Game is not over, create child nodes
      leftChild = new Tree(state.playLeft());
      rightChild = new Tree(state.playRight());
    } else {
      // Game is over, this is a leaf node
      leftChild = null;
      rightChild = null;
    }
  }

  // Minimax implementation for blue player
  private static Triplet minBlue(Triplet leftRes, Triplet rightRes) {
    // Blue player wants to maximize their points
    if (leftRes.getMinBlue() > rightRes.getMinBlue()) {
      return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
    } else if (rightRes.getMinBlue() > leftRes.getMinBlue()) {
      return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
    } else {
      // If minBlue is equal, choose the move that minimizes opponent's points
      if (leftRes.getMinOrange() <= rightRes.getMinOrange()) {
        return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
      } else {
        return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
      }
    }
  }

  // Minimax implementation for orange player
  private static Triplet minOrange(Triplet leftRes, Triplet rightRes) {
    // Orange player wants to maximize their points
    if (leftRes.getMinOrange() > rightRes.getMinOrange()) {
      return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
    } else if (rightRes.getMinOrange() > leftRes.getMinOrange()) {
      return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
    } else {
      // If minOrange is equal, choose the move that minimizes opponent's points
      if (leftRes.getMinBlue() <= rightRes.getMinBlue()) {
        return new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
      } else {
        return new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
      }
    }
  }

  // Compute Minimax values for the entire tree
  public void computeMinimaxValues() {
    if (isLeaf()) {
      // For leaf nodes, create a Triplet with the final scores
      // The move doesn't matter for leaf nodes, so we set it to true
      minimaxValue = new Triplet(true, state.getBluePoints(), state.getOrangePoints());
    } else {
      // Recursively compute Minimax values for children
      leftChild.computeMinimaxValues();
      rightChild.computeMinimaxValues();

      // Determine the best move based on whose turn it is
      if (state.isBlueToPlay()) {
        // Blue player's turn
        minimaxValue = minBlue(leftChild.getMinimaxValue(), rightChild.getMinimaxValue());
      } else {
        // Orange player's turn
        minimaxValue = minOrange(leftChild.getMinimaxValue(), rightChild.getMinimaxValue());
      }
    }
  }

  // Renvoie true si le noeud est une feuille, false sinon.
  public boolean isLeaf() {
    return leftChild == null;
  }

  // Getter de la valeur Minimax
  public Triplet getMinimaxValue() {
    return minimaxValue;
  }

  // Getter de l'�tat courant
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