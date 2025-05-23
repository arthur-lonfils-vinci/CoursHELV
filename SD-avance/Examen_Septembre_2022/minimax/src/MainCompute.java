public class MainCompute {
  // Le jeu est composé de 10 nombres allant de 1 à BOUND compris.
  public static final int BOUND = 5;

  public static void main(String[] args) {
    int[] t = new int[] { 5, 9, 5, 2, 1, 10, 2, 2, 3, 7 }; // false 30 16
    //int[] t = new int[] { 7, 3, 2, 200, 100, 10, 9, 7, 2, 1}; // true 225 116
    //int[] t = new int[] { 1, 2, 7, 9, 10, 100, 200, 2, 3, 7 }; // false 225 116
    
    State state = new State(t);
    Tree tree = new Tree(state);
    tree.computeMinimaxValues();
    Triplet mmValue = tree.getMinimaxValue();
    System.out.println(mmValue);
  }
}
